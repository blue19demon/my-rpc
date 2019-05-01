package com.rpc.core.protocol.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.core.framework.Configure;
import com.rpc.core.framework.Invocation;
import com.rpc.core.framework.RPCConfigure;
import com.rpc.core.framework.URL;
import com.rpc.core.register.MapRegister;

public class JettyDispatchServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setStatus(HttpServletResponse.SC_OK);
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.core.framework.Invocation) ois.readObject();
			Configure conf=RPCConfigure.getConfigure();
			URL url=new URL(conf.getHostname(), conf.getPort());
			Class<?> inplClass=MapRegister.get(invocation.getInterfaceName(), url);
			Method method=inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
			Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
			System.out.println(result);
			ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
			oos.writeObject(result);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
