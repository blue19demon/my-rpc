package com.rpc.diyrpc.provider.mq.api;

public class DemoServiceImpl implements DemoService {

	public String call(String message) {
		return "hello "+message;
	}

}
