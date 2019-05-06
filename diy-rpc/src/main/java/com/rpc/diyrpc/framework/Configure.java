package com.rpc.diyrpc.framework;

public class Configure {

	private String protocol;
	private String hostname;
	private Integer port;
	private String zookeeperHostname;
	private Integer zookeeperPort;
	public String getProtocol() {
		if("http".equals(this.protocol)) {
			return ProviderProtocol.HTTP;
		}else if("jetty".equals(this.protocol)) {
			return ProviderProtocol.JETTY;
		}else if("socket".equals(this.protocol)) {
			return ProviderProtocol.SOCKET;
		}else if("rmi".equals(this.protocol)) {
			return ProviderProtocol.RMI;
		}else if("hessian".equals(this.protocol)) {
			return ProviderProtocol.HESSIAN;
		}else if("redis".equals(this.protocol)) {
			return ProviderProtocol.REDIS;
		}else if("webservice".equals(this.protocol)) {
			return ProviderProtocol.WEBSERVICE;
		}
		return ProviderProtocol.NETTY;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getZookeeperHostname() {
		return zookeeperHostname;
	}
	public void setZookeeperHostname(String zookeeperHostname) {
		this.zookeeperHostname = zookeeperHostname;
	}
	public Integer getZookeeperPort() {
		return zookeeperPort;
	}
	public void setZookeeperPort(Integer zookeeperPort) {
		this.zookeeperPort = zookeeperPort;
	}
	public Configure(String protocol, String hostname, Integer port, String zookeeperHostname, Integer zookeeperPort) {
		super();
		this.protocol = protocol;
		this.hostname = hostname;
		this.port = port;
		this.zookeeperHostname = zookeeperHostname;
		this.zookeeperPort = zookeeperPort;
	}
	public Configure() {
		super();
	}
	@Override
	public String toString() {
		return "Configure [protocol=" + protocol + ", hostname=" + hostname + ", port=" + port + ", zookeeperHostname="
				+ zookeeperHostname + ", zookeeperPort=" + zookeeperPort + "]";
	}
	
}
