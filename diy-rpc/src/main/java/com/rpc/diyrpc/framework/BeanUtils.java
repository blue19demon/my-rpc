package com.rpc.diyrpc.framework;

public class BeanUtils {
	/**
	 * 判断object是否为基本类型
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isBaseType(Class<?> className) {
		if (className.equals(java.lang.Integer.class) || className.equals(java.lang.Byte.class)
				|| className.equals(java.lang.Long.class) || className.equals(java.lang.Double.class)
				|| className.equals(java.lang.Float.class) || className.equals(java.lang.Character.class)
				|| className.equals(java.lang.Short.class) || className.equals(java.lang.String.class)
				|| className.equals(java.lang.Boolean.class)) {
			return true;
		}
		return false;
	}
}
