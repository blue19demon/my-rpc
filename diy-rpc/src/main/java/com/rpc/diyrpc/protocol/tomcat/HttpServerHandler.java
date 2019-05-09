package com.rpc.diyrpc.protocol.tomcat;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.ZKRegister;
public class HttpServerHandler {
	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		try {
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.diyrpc.framework.Invocation) ois.readObject();
			Configure conf=RPCConfigure.getConfigure();
			URL url=new URL(conf.getHostname(), conf.getPort());
			Class<?> inplClass=ZKRegister.get(invocation.getInterfaceName(), url);
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
