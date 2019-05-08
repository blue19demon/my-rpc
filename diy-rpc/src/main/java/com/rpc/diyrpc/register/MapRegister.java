package com.rpc.diyrpc.register;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.I0Itec.zkclient.ZkClient;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.ProviderProtocol;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.protocol.redis.RedissonClientBuilder;

@SuppressWarnings("rawtypes")
public class MapRegister {

	private static Map<String, Map<URL, Class>> REGISTER = new HashMap<>();
	private static ZkClient zk = null;
	private static Map<String,Object> servletHolderMap=new HashMap<>();
	public static String resourceScannerPackage=null;
	static {
		Configure conf = RPCConfigure.getConfigure();
		String connection = conf.getZookeeperHostname() + ":" + conf.getZookeeperPort();
		zk = new ZkClient(connection);
	}

	@SuppressWarnings("unchecked")
	public static void register(Class interfaceClazz, URL url, Class implClass) {
		String interfaceName=interfaceClazz.getName();
		Configure conf = RPCConfigure.getConfigure();
		if (ProviderProtocol.REDIS.equals(conf.getProtocol())) {
			try {
				RedissonClient redisson = RedissonClientBuilder.build();
				RRemoteService remoteService = redisson.getRemoteService();
				remoteService.register(interfaceClazz, implClass.newInstance());
				Map<URL, Class> map = new HashMap<>();
				map.put(url, implClass);
				REGISTER.put(interfaceClazz.getName(), map);
				writeToZK(REGISTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (ProviderProtocol.WEBSERVICE.equals(conf.getProtocol())) {
			String address="http://"+url.getHonename()+":"+url.getPort()+"/"+interfaceName;
			System.out.println(address);
			try {
				Endpoint.publish(address, implClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}else if (ProviderProtocol.HESSIAN.equals(conf.getProtocol())) {
			try {
				servletHolderMap.put("/"+interfaceName, implClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}else if (ProviderProtocol.RMI.equals(conf.getProtocol())) {
			try {
				System.out.println("rmi://"+url.getHonename()+":"+url.getPort()+"/"+interfaceName);
				LocateRegistry.createRegistry(url.getPort());
				java.rmi.Naming.rebind("rmi://"+url.getHonename()+":"+url.getPort()+"/"+interfaceName, (Remote) implClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		Map<URL, Class> map = new HashMap<>();
		map.put(url, implClass);
		REGISTER.put(interfaceName, map);
		writeToZK(REGISTER);
	}
	

	private static void writeToZK(Map<String, Map<URL, Class>> register) {
		if (zk.exists("/zkConfig")) {
			zk.delete("/zkConfig");
		}
		zk.createPersistent("/zkConfig", true);
		zk.writeData("/zkConfig", register);
	}

	public static Class get(String interfaceName, URL url) {
		REGISTER = readFromZK();
		return REGISTER.get(interfaceName).get(url);
	}

	private static Map<String, Map<URL, Class>> readFromZK() {
		return zk.readData("/zkConfig");
	}

	public static Map<String, Object> getServletHolderMap() {
		return servletHolderMap;
	}

	public static URL random(String interfaceName) {
		System.out.println(REGISTER.get(interfaceName));
		URL url = REGISTER.get(interfaceName).keySet().iterator().next();
		if (url == null) {
			url = new URL("localhost", 7777);
		}
		return url;
	}


	public static void restfulResourceScanner(String resourceScanner) {
		resourceScannerPackage=resourceScanner;
	}
}
