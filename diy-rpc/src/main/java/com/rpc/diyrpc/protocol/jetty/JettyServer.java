package com.rpc.diyrpc.protocol.jetty;

import java.net.InetSocketAddress;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {

	public void start(String hostname, Integer port) {

		InetSocketAddress address = new InetSocketAddress(hostname, port);
		Server server = new Server(address);
		ServletContextHandler handler = new ServletContextHandler();
		handler.addServlet(new ServletHolder(new JettyDispatchServlet()), "/*");
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
