package com.rpc.diyrpc.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RPCConfigure {

	private static Configure conf=null;
	static {
		try {
			InputStream inputStream =Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
			Properties pro=new Properties();
			pro.load(inputStream);
			conf=new Configure(
					pro.getProperty("rpc.protocol"),
					pro.getProperty("rpc.host.name"),
					Integer.parseInt(pro.getProperty("rpc.host.port")),
					pro.getProperty("zookeeper.host.name"),
					Integer.parseInt(pro.getProperty("zookeeper.host.port")),
					pro.getProperty("redis.host.name"),
					Integer.parseInt(pro.getProperty("redis.host.port")),
					pro.getProperty("mq.rabbit.host.name"),
					Integer.parseInt(pro.getProperty("mq.rabbit.host.port")),
					pro.getProperty("mq.rabbit.username"),
					pro.getProperty("mq.rabbit.password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Configure getConfigure() {
		return conf;
	}
	
}
