package com.rpc.diyrpc.framework;

public class Configure {

	private String protocol;
	private String hostname;
	private Integer port;
	public String getProtocol() {
		if("http".equals(this.protocol)) {
			return ProviderProtocol.HTTP;
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
	public Configure(String protocol, String hostname, Integer port) {
		super();
		this.protocol = protocol;
		this.hostname = hostname;
		this.port = port;
	}
	public Configure() {
		super();
	}
	@Override
	public String toString() {
		return "Configure [protocol=" + protocol + ", hostname=" + hostname + ", port=" + port + "]";
	}
	
}
