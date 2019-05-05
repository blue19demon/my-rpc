package org.gupao.rpc.api;

public class UserServiceImpl implements UserService {

	@Override
	public String say(String name) {
		return "hello "+name;
	}

	@Override
	public void save(User user) {
		System.out.println(user);
		System.out.println("user saved!!");
	}

}
