package com.rpc.diyrpc.protocol.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	private ExecutorService executorService = Executors.newCachedThreadPool();
	public void start(String hostname, Integer port) {
		ServerSocket ss = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ss = new ServerSocket(port);
			System.out.println("socket server started at 【"+address.getHostAddress()+"】 in 【"+port+"】");
			while (true) {
				Socket socket = ss.accept();
				executorService.execute(new SocketServerHandler(socket));
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
