package com.rpc.diyrpc.protocol.hessian;

import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.caucho.hessian.server.HessianServlet;
import com.rpc.diyrpc.register.MapRegister;

public class HessianServer {

	public void start(String hostname, Integer port) {

		try {
			Server server = new Server(port);
			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath("/");
			server.setHandler(context);
			Map<String, Object> servletHolderMap = MapRegister.getServletHolderMap();
			for (String key : servletHolderMap.keySet()) {
				context.addServlet(new ServletHolder((HessianServlet) servletHolderMap.get(key)), key);
			}
			server.start();
			System.out.println("hessian server started at " + port);
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
