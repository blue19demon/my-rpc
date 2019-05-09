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
import com.rpc.diyrpc.register.ZKRegister;


public class Provider {

	public static void main(String[] args) {
		mq();
	}

	/**
	 * mq
	 */
	public static void mq() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		ZKRegister.register(com.rpc.diyrpc.provider.mq.api.DemoService.class, url, com.rpc.diyrpc.provider.mq.api.DemoServiceImpl.class);
		ZKRegister.register(com.rpc.diyrpc.provider.mq.api.UserService.class, url, com.rpc.diyrpc.provider.mq.api.UserServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}

	/**
	 * webservice
	 */
	public static void webservice() {
		mq();
	}

	/**
	 * restful
	 */
	public static void restful() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		ZKRegister.restfulResourceScanner("com.rpc.diyrpc.provider.rest.api.impl");
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
		ZKRegister.register(SayHelloService.class, url, SayHelloServiceImpl.class);
		ZKRegister.register(DemoService.class, url, DemoServiceImpl.class);
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
		ZKRegister.register(Hello.class, url, HelloImpl.class);
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
		ZKRegister.register(OrderService.class, url, OrderServiceImpl.class);
		ZKRegister.register(HelloService.class, url, HelloServiceImpl.class);
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
		ZKRegister.register(HelloService.class, url, HelloServiceImpl.class);
		ZKRegister.register(DemoService.class, url, DemoServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
}
