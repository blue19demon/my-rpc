package com.rpc.mq.api2;

import com.rpc.mq.anno.RemoteService;

@RemoteService(DemoService.class)
public class DemoServiceImpl implements DemoService {

	public String call(String message) {
		return "hello "+message;
	}

}
