package com.rpc.core.protocol.tomcat;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.core.framework.Configure;
import com.rpc.core.framework.Invocation;
import com.rpc.core.framework.RPCConfigure;
import com.rpc.core.framework.URL;
import com.rpc.core.register.MapRegister;
public class HttpServerHandler {
	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		try {
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.core.framework.Invocation) ois.readObject();
			Configure conf=RPCConfigure.getConfigure();
			URL url=new URL(conf.getHostname(), conf.getPort());
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
