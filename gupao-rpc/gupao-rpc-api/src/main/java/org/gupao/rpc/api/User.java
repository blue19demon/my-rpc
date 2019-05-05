package org.gupao.rpc.api;

import java.io.Serializable;

/**
 * Hello world!
 *
 */
public class User implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9067155081882406510L;
    private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String name) {
		super();
		this.name = name;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
