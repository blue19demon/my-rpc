package net.cc.service.main;

import javax.xml.ws.Endpoint;

import net.cc.service.APIDemoImpl;
import net.cc.service.API_URL;
import net.cc.service.CXFDemoImpl;

public class TestCxfServer{
	public static void main(String[] args) {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected static void setUp() throws Exception {
		System.out.println("Starting Server");
		CXFDemoImpl demo = new CXFDemoImpl();
		Endpoint.publish(API_URL.ADDRESS_DEMO, demo);
		
		APIDemoImpl api = new APIDemoImpl();
		Endpoint.publish(API_URL.ADDRESS_API, api);
		System.out.println("Start success");
	}
}