package com.rpc.diyrpc.provider;

import com.rpc.api.service.DemoService;
import com.rpc.api.service.HelloService;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.ProtocolFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.impl.DemoServiceImpl;
import com.rpc.diyrpc.provider.api.impl.HelloServiceImpl;
import com.rpc.diyrpc.register.MapRegister;

public class Provider {

	public static void main(String[] args) {
		//注册服务
		Configure conf=RPCConfigure.getConfigure();
		URL url=new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(HelloService.class.getName(), url, HelloServiceImpl.class);
		MapRegister.register(DemoService.class.getName(), url, DemoServiceImpl.class);
		//启动
		
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
}
