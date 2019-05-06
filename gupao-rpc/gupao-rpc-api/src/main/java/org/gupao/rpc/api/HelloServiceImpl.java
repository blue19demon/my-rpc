package org.gupao.rpc.api;

public class HelloServiceImpl implements HelloService {

	@Override
	public String say(String name) {
		return "say hello "+name;
	}

	@Override
	public User init(User user) {
		user.setId(2);
		return user;
	}

}
