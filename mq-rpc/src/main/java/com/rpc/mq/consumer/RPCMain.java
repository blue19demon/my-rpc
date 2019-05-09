package com.rpc.mq.consumer;

import java.util.List;

import com.rpc.mq.api.User;
import com.rpc.mq.api.UserService;
import com.rpc.mq.api2.DemoService;
import com.rpc.mq.core.RemoteInterfaceGenarator;

public class RPCMain {

	public static void main(String[] args) throws Exception {

		DemoService demoService = RemoteInterfaceGenarator.genarate(DemoService.class);
		String response = demoService.call("abc");
		System.out.println(response);

		UserService userService = RemoteInterfaceGenarator.genarate(UserService.class);

		List<User> rs = userService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}

		System.out.println(userService.findUserOne("taobao"));
		userService.save(new User("张三丰", "110"));
	}
}