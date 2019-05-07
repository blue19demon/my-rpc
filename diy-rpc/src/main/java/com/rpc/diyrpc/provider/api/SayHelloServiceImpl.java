package com.rpc.diyrpc.provider.api;

public class SayHelloServiceImpl implements SayHelloService {
	@Override
	public String sayHello(String name) {
		System.out.println("你好：" + name);
		return name + ":谢谢你，收到你的问候了";
	}

	@Override
	public void save(String name) {
		System.out.println(name+" saved");
	}
}