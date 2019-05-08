package com.rpc.diyrpc.provider;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.ProtocolFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.DemoServiceImpl;
import com.rpc.diyrpc.provider.api.Hello;
import com.rpc.diyrpc.provider.api.HelloImpl;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.HelloServiceImpl;
import com.rpc.diyrpc.provider.api.OrderService;
import com.rpc.diyrpc.provider.api.OrderServiceImpl;
import com.rpc.diyrpc.provider.api.SayHelloService;
import com.rpc.diyrpc.provider.api.SayHelloServiceImpl;
import com.rpc.diyrpc.provider.webservice.api.PresonService;
import com.rpc.diyrpc.provider.webservice.api.UserDemoService;
import com.rpc.diyrpc.provider.webservice.api.impl.PresonServiceImpl;
import com.rpc.diyrpc.provider.webservice.api.impl.UserDemoServiceImpl;
import com.rpc.diyrpc.register.MapRegister;

public class Provider {

	public static void main(String[] args) {
		webservice();
	}
	/**
	 * webservice
	 */
	public static void webservice() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(PresonService.class, url, PresonServiceImpl.class);
		MapRegister.register(UserDemoService.class, url, UserDemoServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
	/**
	 * restful
	 */
	public static void restful() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.restfulResourceScanner("com.rpc.diyrpc.provider.rest.api.impl");
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}

	/**
	 * redis
	 */
	public static void redis() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(SayHelloService.class, url, SayHelloServiceImpl.class);
		MapRegister.register(DemoService.class, url, DemoServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}

	/**
	 * rmi
	 */
	public static void rmi() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(Hello.class, url, HelloImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}

	/**
	 * hessian
	 */
	public static void hessian() {
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(OrderService.class, url, OrderServiceImpl.class);
		MapRegister.register(HelloService.class, url, HelloServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}


	/**
	 * netty,http,jetty,socket
	 */
	public static void connonProtocol() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(HelloService.class, url, HelloServiceImpl.class);
		MapRegister.register(DemoService.class, url, DemoServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
}
