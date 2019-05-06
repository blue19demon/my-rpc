package com.rpc.diyrpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Naming;

import com.caucho.hessian.client.HessianProxyFactory;

public class ProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(@SuppressWarnings("rawtypes") Class interfaceClass){
		Configure conf=RPCConfigure.getConfigure();
		if (ProviderProtocol.HESSIAN.equals(conf.getProtocol())) {
			URL url=new URL(conf.getHostname(), conf.getPort());
			HessianProxyFactory factory = new HessianProxyFactory();
			Object target = null;
			try {
				target = factory.create(interfaceClass,  
					        "http://"+url.getHonename()+":"+url.getPort()+"/"+interfaceClass.getName());  
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) target;
		}else if (ProviderProtocol.RMI.equals(conf.getProtocol())) {
			URL url=new URL(conf.getHostname(), conf.getPort());
			Object target = null;
			try {
				target = Naming.lookup("rmi://"+url.getHonename()+":"+url.getPort()+"/"+interfaceClass.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) target;
		}else {
			return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
					Invocation invocation = new Invocation();
					invocation.setInterfaceName(interfaceClass.getName());
					invocation.setMethodName(method.getName());
					invocation.setParams(args);
					invocation.setParamTypes(method.getParameterTypes());
					URL url=new URL(conf.getHostname(), conf.getPort());
					Object rs = client.post(url, invocation);
					return rs;
				}
			});
		}
	}
}
