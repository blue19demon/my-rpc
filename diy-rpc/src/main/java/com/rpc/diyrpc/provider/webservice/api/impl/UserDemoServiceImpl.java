package com.rpc.diyrpc.provider.webservice.api.impl;

import com.rpc.diyrpc.provider.webservice.api.UserDemoService;

public class UserDemoServiceImpl implements UserDemoService {

	@Override
	public String sayHello(String foo) {
		return "hello "+foo;
	}

}
