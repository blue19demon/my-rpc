package net.cc.service;

import javax.jws.WebService;

@WebService(serviceName = "cxfDemo")
public class CXFDemoImpl implements CXFDemo {
	public String sayHello(String foo) {
		return "hello " + foo;
	}
}