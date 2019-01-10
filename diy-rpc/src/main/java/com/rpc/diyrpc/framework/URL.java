package com.rpc.diyrpc.framework;

import java.io.Serializable;

public class URL implements Serializable{

	private String honename;
	private Integer port;
	public String getHonename() {
		return honename;
	}
	public void setHonename(String honename) {
		this.honename = honename;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public URL(String honename, Integer port) {
		super();
		this.honename = honename;
		this.port = port;
	}
	public URL() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((honename == null) ? 0 : honename.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URL other = (URL) obj;
		if (honename == null) {
			if (other.honename != null)
				return false;
		} else if (!honename.equals(other.honename))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		return true;
	}
	
}
