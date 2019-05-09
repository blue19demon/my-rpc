package com.rpc.diyrpc.provider.mq.api;

import java.util.List;

public interface UserService {
	public List<User> findUsers(String name);
	public int save(User user);
	public User findUserOne(String name);
}
