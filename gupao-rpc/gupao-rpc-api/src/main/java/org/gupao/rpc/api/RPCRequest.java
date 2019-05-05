package org.gupao.rpc.api;

import java.io.Serializable;

public class RPCRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375998969911401112L;

	private String serviceName;
	private String methodName;
	private Object[] args;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
