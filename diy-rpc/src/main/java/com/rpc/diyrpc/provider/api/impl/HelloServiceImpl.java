package com.rpc.diyrpc.provider.api.impl;

import com.caucho.hessian.server.HessianServlet;
import com.rpc.diyrpc.provider.api.HelloService;

public class HelloServiceImpl extends HessianServlet implements HelloService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8105382775975462556L;

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public void save() {
		System.out.println("save success!!");
	}

}
