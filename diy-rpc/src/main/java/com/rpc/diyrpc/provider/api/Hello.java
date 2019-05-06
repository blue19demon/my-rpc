package com.rpc.diyrpc.provider.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface Hello  extends Remote{
	public String sayHello(String name) throws RemoteException;
	public int save(User user)  throws RemoteException;
}
