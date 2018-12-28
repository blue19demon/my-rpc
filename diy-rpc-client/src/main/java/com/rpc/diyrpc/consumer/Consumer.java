package com.rpc.diyrpc.consumer;

import java.util.List;

import com.rpc.api.entity.User;
import com.rpc.api.service.DemoService;
import com.rpc.diyrpc.framework.ProxyFactory;
public class Consumer {

	public static void main(String[] args) {
		/*HelloService helloService=ProxyFactory.getProxy(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));
		helloService.save();*/
		
		DemoService demoService=ProxyFactory.getProxy(DemoService.class);
		List<User> rs=demoService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}
		//demoService.save(new User("张三丰","110"));
	}
}
