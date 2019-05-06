package com.rpc.diyrpc.consumer;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientRestful {
	public static void main(String[] args) {
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10 * 1000);
			  Client client = Client.create(cc); 
			WebResource resource = client.resource("http://127.0.0.1:10000/users/");  


			  String str = resource  
		                .accept(MediaType.TEXT_PLAIN)  
		                .type(MediaType.TEXT_PLAIN)  
		                .get(String.class);  
		        System.out.println("String:" + str);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

}