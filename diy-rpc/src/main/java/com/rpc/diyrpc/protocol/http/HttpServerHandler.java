package com.rpc.diyrpc.protocol.http;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.MapRegister;
public class HttpServerHandler {
	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		try {
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.diyrpc.framework.Invocation) ois.readObject();
			URL url=new URL("localhost", 8888);
			Class<?> inplClass=MapRegister.get(invocation.getInterfaceName(), url);
			Method method=inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
			Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
			ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
			oos.writeObject(result);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
