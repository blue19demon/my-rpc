package com.rpc.diyrpc.register;

import java.util.HashMap;
import java.util.Map;

import com.rpc.diyrpc.framework.URL;
@SuppressWarnings("rawtypes")
public class MapRegister {

	//{服务名：{URL:实现类}}
	private static Map<String,Map<URL,Class>> REGISTER=new HashMap<>();
	
	public static void register(String interfaceName,URL url,Class implClass) {
		Map<URL,Class> map=new HashMap<>();
		map.put(url, implClass);
		REGISTER.put(interfaceName, map);
	}
	
	public static Class get(String interfaceName,URL url) {
		return REGISTER.get(interfaceName).get(url);
	}
	
	public static URL random(String interfaceName) {
		System.out.println(REGISTER.get(interfaceName));
		URL url=REGISTER.get(interfaceName).keySet().iterator().next();
		if(url==null) {
			url=new URL("localhost", 7777);
		}
		return url;
	}
}
