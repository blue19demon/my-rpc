package com.rpc.api.service;

import java.util.List;

import com.rpc.api.entity.User;

public interface DemoService {
	public List<User> findUsers(String name);
	public void save(User user);
}
