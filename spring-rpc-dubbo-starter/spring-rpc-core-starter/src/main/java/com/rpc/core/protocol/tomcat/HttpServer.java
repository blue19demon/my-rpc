package com.rpc.core.protocol.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

	public void start(String hostname, Integer port) {
		Tomcat tomcat = new Tomcat();
		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");

		Connector connector = new Connector();
		connector.setPort(port);

		Engine engine = new StandardEngine();
		engine.setDefaultHost(hostname);
		Host host = new StandardHost();
		host.setName(hostname);

		Context context = new StandardContext();
		String contextPath = "";
		context.setPath(contextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());
		host.addChild(context);
		engine.addChild(host);
		service.setContainer(engine);
		service.addConnector(connector);

		tomcat.addServlet(contextPath, "dispatct", new DispatchServlet());
		context.addServletMappingDecoded("/*", "dispatct");

		try {
			tomcat.start();
			server.await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
