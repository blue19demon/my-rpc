package com.rpc.diyrpc.provider;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.ProtocolFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.HelloService;
import com.rpc.diyrpc.provider.api.OrderService;
import com.rpc.diyrpc.provider.api.OrderServiceImpl;
import com.rpc.diyrpc.provider.api.impl.HelloServiceImpl;
import com.rpc.diyrpc.register.MapRegister;

public class Provider {

	public static void main(String[] args) {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		//MapRegister.register(Weather.class.getName(), url, WeatherImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}

	public static void publisher() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		URL url = new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(HelloService.class.getName(), url, HelloServiceImpl.class);
		// MapRegister.register(DemoService.class.getName(), url,
		// DemoServiceImpl.class);
		MapRegister.register(OrderService.class.getName(), url, OrderServiceImpl.class);
		// 启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
}
