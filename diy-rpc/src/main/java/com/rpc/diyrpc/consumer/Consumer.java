package com.rpc.diyrpc.consumer;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RemoteInterfaceGenarator;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.Hello;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.Order;
import com.rpc.diyrpc.provider.api.OrderService;
import com.rpc.diyrpc.provider.api.SayHelloService;
import com.rpc.diyrpc.provider.api.User;
import com.rpc.diyrpc.provider.rest.api.Department;
import com.rpc.diyrpc.provider.rest.api.DeptService;
import com.rpc.diyrpc.provider.webservice.api.HelloEntity;
import com.rpc.diyrpc.provider.webservice.api.Preson;
import com.rpc.diyrpc.provider.webservice.api.Preson.PresonBuilder;
import com.rpc.diyrpc.provider.webservice.api.PresonService;
import com.rpc.diyrpc.provider.webservice.api.UserDemoService;
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

	/**
	 * webservice
	 */
	public static void webservice() {
		PresonService presonService = RemoteInterfaceGenarator.genarate(PresonService.class);
		System.out.println(presonService.queryWeather("成都"));
		PresonBuilder presonBuilder =Preson.builder().age(22).userName("张三丰");
		presonBuilder.helloEntityList(Arrays.asList(new HelloEntity("hello"),new HelloEntity("word")));
		presonBuilder.helloEntityMap(new HashMap<String, HelloEntity>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 3818670253379150798L;

			{
				put("key1", new HelloEntity("hello"));
				put("key2", new HelloEntity("word"));
			}
		});
		System.out.println(presonService.getPresonInfo(presonBuilder.build()));
		UserDemoService demoService=RemoteInterfaceGenarator.genarate(UserDemoService.class);
		System.out.println(demoService.sayHello("你们"));
	}

	public static void restful() {
		DeptService deptService = RemoteInterfaceGenarator.genarate(DeptService.class);

		List<Department> list = deptService.list();
		for (Department department : list) {
			System.out.println(department);
		}

		System.out.println(deptService.list02("111", "京津冀"));

		System.out.println(deptService.save("京津冀"));
		System.out.println(deptService.saveMuti(887L, "南京城"));
		System.out.println(deptService.get(1L, "cc"));//get中文会乱码
		System.out.println(deptService.update(1L, "看到"));
		deptService.delete(112L);
		
		com.rpc.diyrpc.provider.rest.api.OrderService orderService = RemoteInterfaceGenarator.genarate(com.rpc.diyrpc.provider.rest.api.OrderService.class);

		List<com.rpc.diyrpc.provider.rest.api.Order> listOrder = orderService.list(new BigDecimal(99.9));
		for (com.rpc.diyrpc.provider.rest.api.Order order : listOrder) {
			System.out.println(order);
		}
	}

	/**
	 * redis
	 */
	public static void redis() {
		SayHelloService helloService = RemoteInterfaceGenarator.genarate(SayHelloService.class);
		helloService.save("a");
		String result = helloService.sayHello("小陈博主");
		System.out.println("远程服务返回结果：" + result);

		DemoService demoService = RemoteInterfaceGenarator.genarate(DemoService.class);
		List<User> rs = demoService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}

	}

	/**
	 * restfulOld
	 */
	public static void restfulOld() {
		try {
			Configure conf = RPCConfigure.getConfigure();
			String serverHost = "http://" + conf.getHostname() + ":" + conf.getPort();
			String deptAPI = serverHost + "/deptAPI";

			ClientConfig cc = new DefaultClientConfig();
			cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10 * 1000);
			Client client = Client.create(cc);
			// 插入参数param1,param2
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("id", "112");
			queryParams.add("name", "信息技术部");
			WebResource resource = client.resource(deptAPI + "/listGet");
			// 返回类型可选的
			String str = resource.queryParams(queryParams)
					./* accept(MediaType.APPLICATION_JSON)//默认就是返回xml. */get(String.class);
			System.out.println(str);

			resource = client.resource(deptAPI + "/");
			str = resource.post(ClientResponse.class, queryParams).getEntity(String.class);//
			System.out.println(str);

			resource = client.resource(deptAPI + "/33");
			str = resource.put(ClientResponse.class, queryParams).getEntity(String.class);//
			System.out.println(str);

			resource = client.resource(deptAPI + "/33");
			resource.delete(ClientResponse.class);// 参数列表里加入obj对象

			resource = client.resource(deptAPI + "/33/AA");// 中文乱码
			str = resource.get(String.class);//
			System.out.println(str);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * rmi
	 */
	public static void rmi() {
		Hello hello = RemoteInterfaceGenarator.genarate(Hello.class);
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
		OrderService OrderService = RemoteInterfaceGenarator.genarate(OrderService.class);
		Order order = new Order();
		order.setUserName("zz");
		System.out.println(OrderService.helloWorld("hessian"));
		System.out.println(OrderService.getMyInfo(order));

		HelloService helloService = RemoteInterfaceGenarator.genarate(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));
		helloService.save();
	}

	/**
	 * netty,http,jetty,socket
	 */
	public static void connonProtocol() {
		HelloService helloService = RemoteInterfaceGenarator.genarate(HelloService.class);
		System.out.println(helloService.sayHello("RPC"));

		helloService.save();

		DemoService demoService = RemoteInterfaceGenarator.genarate(DemoService.class);

		List<User> rs = demoService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}

		demoService.save(new User("张三丰", "110"));
	}
}
