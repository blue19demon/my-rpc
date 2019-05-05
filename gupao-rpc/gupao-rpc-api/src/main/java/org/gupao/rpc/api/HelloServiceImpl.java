package org.gupao.rpc.api;

public class HelloServiceImpl implements HelloService {

	@Override
	public String say(String name) {
		return "say hello "+name;
	}

}
