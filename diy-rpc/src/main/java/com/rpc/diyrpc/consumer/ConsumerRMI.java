package com.rpc.diyrpc.consumer;

import java.rmi.RemoteException;

import com.rpc.diyrpc.framework.ProxyFactory;
import com.rpc.diyrpc.provider.api.Hello;
import com.rpc.diyrpc.provider.api.User;

public class ConsumerRMI {
	public static void main(String[] args) {
		Hello hello = ProxyFactory.getProxy(Hello.class);
		try {
			System.out.println(hello.sayHello("RMI"));
			System.out.println(hello.save(new User("张三丰", "110")));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
