package com.rpc.diyrpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.rpc.diyrpc.protocol.http.Protocol;

public class ProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(@SuppressWarnings("rawtypes") Class interfaceClass){
		Configure conf=RPCConfigure.getConfigure();
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
