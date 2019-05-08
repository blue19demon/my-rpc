package com.rpc.diyrpc.provider.webservice.api;
import javax.jws.WebService;

@WebService
public interface UserDemoService {
     public String sayHello(String foo);
}