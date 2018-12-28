package com.rpc.api.entity;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", phone=" + phone + "]";
	}
	public User(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	public User() {
		super();
	}
	
}
