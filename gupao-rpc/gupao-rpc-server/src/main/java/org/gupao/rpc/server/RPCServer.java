package org.gupao.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public void register(Object service, int port) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			while (true) {
				Socket socket = ss.accept();
				executorService.execute(new ServerHandler(service, socket));
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
