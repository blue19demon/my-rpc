package com.rpc.diyrpc.provider.api.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.rpc.diyrpc.provider.api.DemoService;
import com.rpc.diyrpc.provider.api.User;

public class DemoServiceImpl implements DemoService {

	private static List<User> collec = Collections.synchronizedList(
			Arrays.asList(
			new User("张三", "18402850503"),
			new User("张麻子", "18076575691"),
			new User("王五", "15708456787")
			)
		);
	@Override
	public List<User> findUsers(String name) {
		return  collec.stream()  
		        .filter(line -> line.getName().contains(name)) 
		        .collect(Collectors.toList());
	}

	@Override
	public int save(User user) {
		collec.add(user);
		System.out.println("保存："+user+"，成功");
		return 1;
	}

}
