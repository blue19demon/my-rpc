package com.rpc.mq.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import com.rpc.mq.anno.RemoteService;

@RemoteService(UserService.class)
public class UserServiceImpl implements UserService {

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

	@Override
	public User findUserOne(String name) {
		User u = collec.get(new Random().nextInt(collec.size()));
		u.setName(name);
		return u;
	}

}
