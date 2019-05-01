package com.rpc.core.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rpc.core.framework.Configure;
import com.rpc.core.framework.ProtocolFactory;
import com.rpc.core.framework.RPCConfigure;
import com.rpc.core.framework.URL;
import com.rpc.core.protocol.tomcat.Protocol;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProviderStarter {
	@Bean
	public Object starter() {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
		URL url=new URL(conf.getHostname(), conf.getPort());
		client.start(url);
		log.info("privider started...url="+url);
		return null;
	}
}
