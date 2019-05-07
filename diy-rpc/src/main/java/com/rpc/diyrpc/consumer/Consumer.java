package com.rpc.diyrpc.consumer;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.ProxyFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.Hello;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.Order;
import com.rpc.diyrpc.provider.api.OrderService;
import com.rpc.diyrpc.provider.api.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Consumer {

	public static void main(String[] args) {
		restful();
	}

	public static void restful() {
		try {
			Configure conf = RPCConfigure.getConfigure();
			String serverHost="http://" + conf.getHostname() + ":" + conf.getPort() ;
			String deptAPI=serverHost+"/deptAPI";
			
			ClientConfig cc = new DefaultClientConfig();
			cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10 * 1000);
			Client client = Client.create(cc);
			//插入参数param1,param2
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("id","112");
			queryParams.add("name", "信息技术部");
			WebResource resource = client.resource(deptAPI+"/listGet");
			//返回类型可选的
			String str = resource.queryParams(queryParams)./* accept(MediaType.APPLICATION_JSON)//默认就是返回xml. */get(String.class);
			System.out.println(str);
		
			resource = client.resource(deptAPI+ "/");
			str=resource.post(ClientResponse.class,queryParams).getEntity(String.class);//参数列表里加入obj对象
			System.out.println(str);
			
			resource = client.resource(deptAPI + "/33");
			str=resource.put(ClientResponse.class,queryParams).getEntity(String.class);//参数列表里加入obj对象
			System.out.println(str);
			
			resource = client.resource(deptAPI + "/33");
			resource.delete(ClientResponse.class);//参数列表里加入obj对象
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void rmi() {
		Hello hello = ProxyFactory.getProxy(Hello.class);
		try {
			System.out.println(hello.sayHello("RMI"));
			System.out.println(hello.save(new User("张三丰", "110")));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * hessian
	 */
	public static void hessian() {
		OrderService OrderService = ProxyFactory.getProxy(OrderService.class);
		Order order = new Order();
		order.setUserName("zz");
		System.out.println(OrderService.helloWorld("hessian"));
		System.out.println(OrderService.getMyInfo(order));

		HelloService helloService = ProxyFactory.getProxy(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));
		helloService.save();
	}

	/**
	 * netty,http,jetty,socket
	 */
	public static void connonProtocol() {
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
