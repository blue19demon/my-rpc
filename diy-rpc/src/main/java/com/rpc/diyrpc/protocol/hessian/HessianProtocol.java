package com.rpc.diyrpc.protocol.hessian;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;

public class HessianProtocol implements Protocol {

	@Override
	public void start(com.rpc.diyrpc.framework.URL url) {
		// 启动
		HessianServer server = new HessianServer();
		server.start(url.getHonename(), url.getPort());
	}

	@Override
	public Object post(com.rpc.diyrpc.framework.URL urlParam, Invocation invocation) {
		return null;
	}
}
