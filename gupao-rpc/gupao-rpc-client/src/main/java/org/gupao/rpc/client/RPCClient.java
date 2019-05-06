package org.gupao.rpc.client;

import java.lang.reflect.Proxy;

public class RPCClient {

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> interfaceClass,int port) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[] {interfaceClass} , new ClientProxy(port,interfaceClass));
	}
}
