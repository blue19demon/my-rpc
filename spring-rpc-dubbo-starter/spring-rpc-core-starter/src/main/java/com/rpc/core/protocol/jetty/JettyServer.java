package com.rpc.core.protocol.jetty;

import java.net.InetSocketAddress;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {

	public void start(String hostname, Integer port) {
		InetSocketAddress address = new InetSocketAddress(hostname,port);
		// 新建web服务器
		Server server = new Server(address);
		// 添加自定义的Servlet
		ServletContextHandler handler = new ServletContextHandler();
		handler.addServlet(new ServletHolder(new JettyDispatchServlet()),"/*");
		server.setHandler(handler);
		// 启动web服务器
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
