package com.rpc.core.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;

import com.rpc.core.anno.RemoteService;
import com.rpc.core.framework.Configure;
import com.rpc.core.framework.RPCConfigure;
import com.rpc.core.framework.URL;
import com.rpc.core.register.MapRegister;

public class ServiceAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
		implements PriorityOrdered {

	private int order = Ordered.LOWEST_PRECEDENCE - 1;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		RemoteService remoteService = AnnotationUtils.findAnnotation(bean.getClass(), RemoteService.class);
		Object resultBean = bean;
		if (null != remoteService) {
			//注册服务
			Configure conf=RPCConfigure.getConfigure();
			URL url=new URL(conf.getHostname(), conf.getPort());
			Class<?> service = bean.getClass();
			MapRegister.register(service.getInterfaces()[0].getName(), url, service);
		}
		return resultBean;
	}

	@Override
	public int getOrder() {
		return order;
	}
}