package com.rpc.diyrpc.framework;

import com.rpc.diyrpc.protocol.jetty.JettyProtocol;
import com.rpc.diyrpc.protocol.netty.NettyProtocol;
import com.rpc.diyrpc.protocol.tomcat.HttpProtocol;
import com.rpc.diyrpc.protocol.tomcat.Protocol;

public class ProtocolFactory {

	public static Protocol getProtocol(String type) {
		Protocol protocol = null;
		switch (type) {
		case ProviderProtocol.NETTY:
			protocol=new NettyProtocol();
			break;
		case ProviderProtocol.JETTY:
			protocol = new JettyProtocol();
			break;
		case ProviderProtocol.HTTP:
			protocol = new HttpProtocol();
			break;
		default:
			protocol = new HttpProtocol();
			break;
		}
		return protocol;
	}
}
