package com.rpc.core.consumer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.rpc.core.framework.ProxyFactory;

/**
 * 自定义的代理工厂，用于将指定的接口转换为代理实体
 */
public class MyRmiProxyFactory<T> implements FactoryBean<T>,InitializingBean {

    private Class<T> interfaceClass;
    public MyRmiProxyFactory() {
    }

    public MyRmiProxyFactory(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public MyRmiProxyFactory(String url,Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @SuppressWarnings("unchecked")
	public T createBean(){
        return (T)ProxyFactory.getProxy(interfaceClass);
    }

    @Override
    public T getObject() throws Exception {
        return createBean();
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}