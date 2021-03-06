package com.rpc.core.protocol.tomcat;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rpc.core.framework.Invocation;

public class HttpProtocol implements Protocol {

	@Override
	public void start(com.rpc.core.framework.URL url) {
		// 启动
		HttpServer server = new HttpServer();
		server.start(url.getHonename(), url.getPort());
	}

	@Override
	public Object post(com.rpc.core.framework.URL urlParam, Invocation invocation) {
		try {
			URL url = new URL("http", urlParam.getHonename(), urlParam.getPort(), "/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(invocation);
			oos.flush();
			oos.close();
			InputStream inputStream = connection.getInputStream();
			if(inputStream!=null) {
				ObjectInputStream ois=new ObjectInputStream(inputStream);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
