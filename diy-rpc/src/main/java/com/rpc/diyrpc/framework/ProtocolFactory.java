package com.rpc.diyrpc.framework;

import com.rpc.diyrpc.protocol.http.HttpProtocol;
import com.rpc.diyrpc.protocol.http.Protocol;
import com.rpc.diyrpc.protocol.netty.NettyProtocol;

public class ProtocolFactory {

	public static Protocol getProtocol(String type) {
		Protocol protocol = null;
		switch (type) {
		case ProviderProtocol.NETTY:
			protocol=new NettyProtocol();
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
