package com.rpc.diyrpc.protocol.webservice;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.URL;

public class WebServiceProtocol implements Protocol{
	
	@Override
	public void start(URL url) {
		WebServiceServer server = new WebServiceServer();
		server.start(url.getHostName(), url.getPort());
	}

	@Override
	public Object post(URL url, Invocation invocation) {
		return null;
	}
}
