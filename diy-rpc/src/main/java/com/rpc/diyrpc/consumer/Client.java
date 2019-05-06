package com.rpc.diyrpc.consumer;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.rpc.diyrpc.provider.api.Weather;

public class Client {  
    public static void main(String[] args) {  
    	try {  
    		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    		factory.setServiceClass(Weather.class);
    		factory.setAddress("http://127.0.0.1:7777/Weather");
    		Weather client = (Weather) factory.create();
    		String result=client.queryWeather("南京");
            System.out.println("result is " + result);  
        } catch (Exception e) {  
            System.err.println(e.toString());  
        }
    }

} 