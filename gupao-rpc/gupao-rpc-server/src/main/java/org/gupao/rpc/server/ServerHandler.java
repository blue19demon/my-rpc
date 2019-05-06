package org.gupao.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

import org.gupao.rpc.api.RPCRequest;

public class ServerHandler implements Runnable {
	private Map<String, Object> serviceList;
	private Socket socket;

	public ServerHandler(Map<String, Object> serviceList, Socket socket) {
		super();
		this.socket = socket;
		this.serviceList = serviceList;
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

	private Object handleRpc(RPCRequest rpcRequest) {
		Object[] args = rpcRequest.getArgs();
		Class<?>[] paramTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			paramTypes[i] = args[i].getClass();
		}
		try {
			Object service = null;
			for (String interfaceName : serviceList.keySet()) {
				if (rpcRequest.getInterfaceName().equals(interfaceName)) {
					service = serviceList.get(interfaceName);
					break;
				}
			}
			Method m = service.getClass().getMethod(rpcRequest.getMethodName(), paramTypes);
			try {
				return m.invoke(service, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
