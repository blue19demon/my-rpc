package com.rpc.diyrpc.provider.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoServiceImpl implements DemoService {

	private static List<User> collec = new ArrayList<>();
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
	public int save(User user) {
		collec.add(user);
		System.out.println("保存："+user+"，成功");
		return 1;
	}

}
