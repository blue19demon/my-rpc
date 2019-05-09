package com.rpc.diyrpc.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.URL;

public class SocketProtocol implements Protocol{
	
	@Override
	public void start(URL url) {
		SocketServer server = new SocketServer();
		server.start(url.getHostName(), url.getPort());
	}

	@Override
	public Object post(URL url, Invocation invocation) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket socket=null;
		try {
			socket=new Socket(url.getHostName(), url.getPort());
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(invocation);
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
