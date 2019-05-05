package org.gupao.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import org.gupao.rpc.api.RPCRequest;

public class ServerHandler implements Runnable {
	private Object service;
	private Socket socket;

	public ServerHandler(Object service, Socket socket) {
		super();
		this.service = service;
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			RPCRequest reRpcRequest = (RPCRequest) ois.readObject();
			Object result = handleRpc(reRpcRequest);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(result);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Object handleRpc(RPCRequest reRpcRequest) {
		Object[] args = reRpcRequest.getArgs();
		Class<?>[] paramTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			paramTypes[i] = args[i].getClass();
		}
		try {
			Method m = service.getClass().getMethod(reRpcRequest.getMethodName(), paramTypes);
			try {
				return m.invoke(service, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
