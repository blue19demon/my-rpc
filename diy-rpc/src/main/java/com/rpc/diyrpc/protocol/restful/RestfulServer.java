package com.rpc.diyrpc.protocol.restful;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class RestfulServer {

	public void start(String hostname, Integer port) {
		URI uri = UriBuilder.fromUri("http://127.0.0.1").port(10000).build();
		ResourceConfig rc = new PackagesResourceConfig("com.rpc.diyrpc.provider.rest.api");
		try {
			HttpServer server = GrizzlyServerFactory.createHttpServer(uri, rc);
			server.start();
			System.out.println("RestfulServer started");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
