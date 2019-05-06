package net.cc.service;

import javax.xml.ws.Endpoint;
 
public class ServiceMain {
	public static void main(String args[]){  
        HelloWorld hw = new HelloWorldImpl();  
        //调用Endpoint的publish方法发布Web Service  
        Endpoint.publish("http://127.0.0.1/HelloWorld", hw);  
        System.out.println("Web Service暴露成功！");  
    } 

}