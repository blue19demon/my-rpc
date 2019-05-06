package net.cc.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author test
 * @create 2013-11-26下午10:21:13
 */
@WebService
public interface HelloWorld {

    String sayHello(@WebParam(name = "userName") String userName);

}