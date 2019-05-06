package org.gupao.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import org.gupao.rpc.api.RPCRequest;

public class ClientProxy implements InvocationHandler{
	private int port;
	private Class<?> interfaceClass;
	public ClientProxy(int port,Class<?> interfaceClass) {
		super();
		this.port = port;
		this.interfaceClass=interfaceClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket socket=null;
		try {
			socket=new Socket("127.0.0.1", port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			RPCRequest reRpcRequest = new RPCRequest();
			reRpcRequest.setArgs(args);
			reRpcRequest.setMethodName(method.getName());
			reRpcRequest.setInterfaceName(interfaceClass.getName());
			oos.writeObject(reRpcRequest);
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(socket!=null) {
				socket.close();
			}
		}
		return null;
	}

}
