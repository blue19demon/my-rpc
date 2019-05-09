package com.rpc.diyrpc.protocol.rmi;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.URL;

public class RMIProtocol implements Protocol{
	
	@Override
	public void start(URL url) {
		RMIServer server = new RMIServer();
		server.start(url.getHostName(), url.getPort());
	}

	@Override
	public Object post(URL url, Invocation invocation) {
		return null;
	}
}
