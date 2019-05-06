package net.cc.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author test
 * @create 2013-11-26下午10:22:53
 */
@WebService(serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHello(@WebParam(name = "userName") String userName) {
        // TODO Auto-generated method stub
        System.out.println("客户端提交信息： " + userName);
        return "say Hello " + userName;
    }
}