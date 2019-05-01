package com.rpc.core.protocol.tomcat;

import com.rpc.core.framework.Invocation;
import com.rpc.core.framework.URL;

public interface Protocol {

	public void start(URL url);
	
	public Object post(URL url,Invocation invocation);
}
