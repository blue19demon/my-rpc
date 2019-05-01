package com.rpc.core.framework;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configure {

	private String protocol;
	private String hostname;
	private Integer port;
	private String zookeeperHostname;
	private Integer zookeeperPort;
	public String getProtocol() {
		this.protocol=(String) PropertiesUtils.getCommonYml("rpc.protocol");
		if("http".equals(this.protocol)) {
			return ProviderProtocol.HTTP;
		}else if("jetty".equals(this.protocol)) {
			return ProviderProtocol.JETTY;
		}
		return ProviderProtocol.NETTY;
	}
	public String getHostname() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.hostname = address.getHostAddress();
		return hostname;
	}
	public Integer getPort() {
		return (Integer) PropertiesUtils.getCommonYml("rpc.port");
	}
	public String getZookeeperHostname() {
		return (String) PropertiesUtils.getCommonYml("rpc.zookeeper.address");
	}
	public void setZookeeperHostname(String zookeeperHostname) {
		this.zookeeperHostname = zookeeperHostname;
	}
	public Integer getZookeeperPort() {
		return (Integer) PropertiesUtils.getCommonYml("rpc.zookeeper.port");
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
