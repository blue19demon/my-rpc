package com.rpc.diyrpc.consumer;

import java.util.List;

import com.rpc.diyrpc.framework.ProxyFactory;
import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.Order;
import com.rpc.diyrpc.provider.api.OrderService;
import com.rpc.diyrpc.provider.api.User;

public class Consumer {

	public static void main(String[] args) {
		OrderService OrderService = ProxyFactory.getProxy(OrderService.class);
		Order order=new Order();
		order.setUserName("zz");
		System.out.println(OrderService.helloWorld("hessian"));
		System.out.println(OrderService.getMyInfo(order));
		
		HelloService helloService = ProxyFactory.getProxy(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));
		helloService.save();
	}

	public static void rmi() {
		HelloService helloService = ProxyFactory.getProxy(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));

		helloService.save();

		DemoService demoService = ProxyFactory.getProxy(DemoService.class);

		List<User> rs = demoService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}

		demoService.save(new User("张三丰", "110"));
	}
}
