package com.rpc.diyrpc.provider.api;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
public class HelloImpl extends UnicastRemoteObject implements Hello {
	private static final long serialVersionUID = -271947229644133464L;
 
	public HelloImpl() throws RemoteException{
		super();
	}
 
	public String sayHello(String name) throws RemoteException {
		return "Hello,"+name;
	}

	@Override
	public int save(User user) throws RemoteException {
		System.out.println("保存："+user+"，成功");
		return 1;
	}
}