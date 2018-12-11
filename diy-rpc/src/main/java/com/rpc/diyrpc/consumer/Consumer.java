package com.rpc.diyrpc.consumer;

import java.util.List;

import com.rpc.diyrpc.framework.ProxyFactory;
import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.User;

public class Consumer {

	public static void main(String[] args) {
		HelloService helloService=ProxyFactory.getProxy(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));
		helloService.save();
		
		/*DemoService demoService=ProxyFactory.getProxy(DemoService.class);
		System.out.println("demoService=="+demoService);
		List<User> rs=demoService.findUsers("张");
		System.out.println(rs);
		for (User user : rs) {
			System.out.println(user);
		}*/
		//demoService.save(new User("张三丰","110"));
	}
}
