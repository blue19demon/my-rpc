package com.rpc.diyrpc.protocol.mq;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;

public class MQProtocol implements Protocol {

	@Override
	public void start(com.rpc.diyrpc.framework.URL url) {
		// 启动
		MQServer server = new MQServer();
		server.start(url.getHostName(), url.getPort());
	}

	@Override
	public Object post(com.rpc.diyrpc.framework.URL urlParam, Invocation invocation) {
		return null;
	}
}
