package com.rpc.diyrpc.protocol.redis;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;

public class RedisProtocol implements Protocol {

	@Override
	public void start(com.rpc.diyrpc.framework.URL url) {
		System.out.println("redis server started");
	}

	@Override
	public Object post(com.rpc.diyrpc.framework.URL urlParam, Invocation invocation) {
		return null;
	}
}
