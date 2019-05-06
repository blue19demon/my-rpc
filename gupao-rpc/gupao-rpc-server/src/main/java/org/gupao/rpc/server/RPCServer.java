package org.gupao.rpc.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private static Map<String,Object> serviceList=new HashMap<>();
	public static void register(Object service) {
		serviceList.put(service.getClass().getInterfaces()[0].getName(),service);
	}
	public void start(int port) {
		ServerSocket ss = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ss = new ServerSocket(port);
			System.out.println("server started at 【"+address.getHostAddress()+"】 in 【"+port+"】");
			while (true) {
				Socket socket = ss.accept();
				executorService.execute(new ServerHandler(serviceList, socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
