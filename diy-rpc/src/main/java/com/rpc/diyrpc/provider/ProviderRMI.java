package com.rpc.diyrpc.provider;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.ProtocolFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.Hello;
import com.rpc.diyrpc.provider.api.HelloImpl;
import com.rpc.diyrpc.register.MapRegister;

public class ProviderRMI {

	public static void main(String[] args) {
		//注册服务
		Configure conf=RPCConfigure.getConfigure();
		URL url=new URL(conf.getHostname(), conf.getPort());
		MapRegister.register(Hello.class.getName(), url, HelloImpl.class);
		//启动
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		client.start(url);
	}
}
