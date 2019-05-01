package com.rpc.core.framework;

import java.io.Serializable;
import java.util.Arrays;
@SuppressWarnings("rawtypes")
public class Invocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5917425187821512650L;

	private String interfaceName;
	
	private String methodName;
	
	private Object[] params;
	
	private Class[] paramTypes;
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public Class[] getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramTypes) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.params = params;
		this.paramTypes = paramTypes;
	}
	public Invocation() {
		super();
	}
	@Override
	public String toString() {
		return "Invocation [interfaceName=" + interfaceName + ", methodName=" + methodName + ", params="
				+ Arrays.toString(params) + ", paramTypes=" + Arrays.toString(paramTypes) + "]";
	}
	
}
