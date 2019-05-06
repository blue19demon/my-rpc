package net.cc.service;
import javax.jws.WebService;

@WebService
public interface CXFDemo {
     public String sayHello(String foo);
}