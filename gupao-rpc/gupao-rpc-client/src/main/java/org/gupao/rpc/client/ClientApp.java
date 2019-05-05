package org.gupao.rpc.client;

import org.gupao.rpc.api.HelloService;
import org.gupao.rpc.api.User;
import org.gupao.rpc.api.UserService;

/**
 * Hello world!
 *
 */
public class ClientApp 
{
    public static void main( String[] args )
    {
    	RPCClient RPCClient=new RPCClient();
    	UserService UserService=RPCClient.getInstance(UserService.class, 8080);
    	System.out.println(UserService.say("mac"));
    	UserService.save(new User("尼玛"));
    	
    	HelloService HelloService=RPCClient.getInstance(HelloService.class, 8080);
    	System.out.println(HelloService.say("王尼玛"));
    }
}
