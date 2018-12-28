package com.rpc.diyrpc.provider.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rpc.api.entity.User;
import com.rpc.api.service.DemoService;

public class DemoServiceImpl implements DemoService {

	private static ArrayList<User> collec =new ArrayList<User>();
	static {
		collec.add(new User("张三", "18402850503"));
		collec.add(new User("张麻子", "18076575691"));
		collec.add(new User("王五", "15708456787"));
	}
	@Override
	public List<User> findUsers(String name) {
		return  collec.stream()  
		        .filter(line -> line.getName().contains(name)) 
		        .collect(Collectors.toList());
	}

	@Override
	public void save(User user) {
		collec.add(user);
		System.out.println("保存："+user+"，成功");
	}

}
