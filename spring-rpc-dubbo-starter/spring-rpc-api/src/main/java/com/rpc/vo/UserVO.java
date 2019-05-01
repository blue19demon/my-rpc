package com.rpc.vo;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;

	private String userName;

	private String password;

	private String phone;

	public Integer getUserId() {
		return userId;
	}

	public UserVO setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UserVO setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserVO setPassword(String password) {
		this.password = password == null ? null : password.trim();
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UserVO setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
		return this;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", phone=" + phone
				+ "]";
	}

}
