package com.rpc.mq.registry;

import java.util.HashMap;
import java.util.Map;

import com.rpc.mq.tools.URL;

public class Registry {

	private static Map<String, Map<URL, Class<?>>> REGISTER = new HashMap<>();
	

	public static void register(Class<?> interfaceClazz, URL url, Class<?> implClass) {
		String interfaceName=interfaceClazz.getName();
		Map<URL, Class<?>> map = new HashMap<>();
		map.put(url, implClass);
		REGISTER.put(interfaceName, map);
	}
	
	public static Class<?> get(String interfaceName, URL url) {
		return REGISTER.get(interfaceName).get(url);
	}

	
}
