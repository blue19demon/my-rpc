
package com.rpc.diyrpc.provider.rest.api;

import java.io.Serializable;

public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3267325471310449128L;
	private String userId;
	private String name;
	private String age;

	public User() {
		super();
	}

	public User(String userId, String name, String age) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age + "]";
	}

}
