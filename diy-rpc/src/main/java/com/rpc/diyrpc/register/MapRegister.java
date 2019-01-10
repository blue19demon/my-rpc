package com.rpc.diyrpc.register;

import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
@SuppressWarnings("rawtypes")
public class MapRegister {

	//{服务名：{URL:实现类}}
	private static Map<String,Map<URL,Class>> REGISTER=new HashMap<>();
	private static ZkClient zk = null;
	static {
		Configure conf=RPCConfigure.getConfigure();
		String connection=conf.getZookeeperHostname()+":"+conf.getZookeeperPort();
		zk = new ZkClient(connection);
	}
	public static void register(String interfaceName,URL url,Class implClass) {
		Map<URL,Class> map=new HashMap<>();
		map.put(url, implClass);
		REGISTER.put(interfaceName, map);
		writeToZK(REGISTER);
	}
	
	private static void writeToZK(Map<String, Map<URL, Class>> register) {
		if(zk.exists("/zkConfig")){
			zk.delete("/zkConfig");
		}
		zk.createPersistent("/zkConfig",true);
		zk.writeData("/zkConfig", register);
		//zk.close();
	}

	public static Class get(String interfaceName,URL url) {
		REGISTER=readFromZK();
		return REGISTER.get(interfaceName).get(url);
	}
	
	private static Map<String, Map<URL, Class>> readFromZK() {
		return zk.readData("/zkConfig");
	}

	public static URL random(String interfaceName) {
		System.out.println(REGISTER.get(interfaceName));
		URL url=REGISTER.get(interfaceName).keySet().iterator().next();
		if(url==null) {
			url=new URL("localhost", 7777);
		}
		return url;
	}
}
