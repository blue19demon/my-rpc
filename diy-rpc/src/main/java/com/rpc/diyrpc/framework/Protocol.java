package com.rpc.diyrpc.framework;

public interface Protocol {

	public void start(URL url);
	
	public Object post(URL url,Invocation invocation);
}
