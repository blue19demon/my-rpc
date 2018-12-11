package com.rpc.diyrpc.provider.api;

import java.util.List;

public interface DemoService {
	public List<User> findUsers(String name);
	public int save(User user);
}
