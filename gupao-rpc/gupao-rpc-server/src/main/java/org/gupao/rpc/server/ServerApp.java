package org.gupao.rpc.server;

import org.gupao.rpc.api.HelloService;
import org.gupao.rpc.api.HelloServiceImpl;
import org.gupao.rpc.api.UserService;
import org.gupao.rpc.api.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class ServerApp 
{
    public static void main( String[] args )
    {
    	RPCServer rpcServer=new RPCServer();
    	UserService UserService=new UserServiceImpl();
    	HelloService HelloService=new HelloServiceImpl();
    	RPCServer.register(UserService);
    	RPCServer.register(HelloService);
    	rpcServer.start(8080);
    }
}
