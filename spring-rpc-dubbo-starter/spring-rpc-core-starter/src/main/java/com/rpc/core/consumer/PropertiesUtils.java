package com.rpc.core.consumer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by admin on 2019/1/4.
 */
public class PropertiesUtils {
    private static String PROPERTY_NAME = "application.yml";

    public static Object getCommonYml(Object key){
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.getCommonYml("rmi.url"));
        System.out.println(PropertiesUtils.getArrayYml("rmi.remoteClientScan.basePackages"));
    }

	public static List<String> getArrayYml(String key) {
		String value=(String) getCommonYml(key);
		if("".equals(value)||null==value) {
			return null;
		}
		List<String> array=new ArrayList<String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			array.add(values[i]);
		}
        return array;
	}
}