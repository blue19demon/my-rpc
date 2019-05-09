package com.rpc.mq.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rpc.mq.tools.BeanUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

@SuppressWarnings("deprecation")
public class RemoteInterfaceGenarator {

	@SuppressWarnings("unchecked")
	public static <T> T genarate(Class<? extends T> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Connection connection;
						Channel channel;
						String requestQueueName = "rpc_queue_service";
						String replyQueueName;
						QueueingConsumer consumer;
						// • 先建立一个连接和一个通道，并为回调声明一个唯一的'回调'队列
						ConnectionFactory factory = new ConnectionFactory();
						factory.setHost("127.0.0.1");
						factory.setUsername("eric");
						factory.setPassword("123456");
						factory.setPort(AMQP.PROTOCOL.PORT);
						connection = factory.newConnection();
						channel = connection.createChannel();
						// • 注册'回调'队列，这样就可以收到RPC响应
						replyQueueName = channel.queueDeclare().getQueue();
						consumer = new QueueingConsumer(channel);
						channel.basicConsume(replyQueueName, true, consumer);

						String corrId = java.util.UUID.randomUUID().toString();
						// 发送请求消息，消息使用了两个属性：replyto和correlationId
						BasicProperties props = new BasicProperties.Builder().correlationId(corrId)
								.replyTo(replyQueueName).build();
						Invocation invocation = new Invocation();
						invocation.setInterfaceName(interfaceClass.getName());
						invocation.setMethodName(method.getName());
						invocation.setParams(args);
						invocation.setParamTypes(method.getParameterTypes());
						
						//channel.basicPublish("", requestQueueName, props,args[0].toString().getBytes());
						channel.basicPublish("", requestQueueName,props,BeanUtils.ObjectToByte(invocation));
						// 等待接收结果
						Class<?> rt = method.getReturnType();
						while (true) {
							QueueingConsumer.Delivery delivery = consumer.nextDelivery();
							// 检查它的correlationId是否是我们所要找的那个
							if (delivery.getProperties().getCorrelationId().equals(corrId)) {
								if (!rt.equals(void.class)) {
									connection.close();  
									return BeanUtils.ByteToObject(delivery.getBody());
								}
								connection.close();  
								break;
							}
						}
						return null;
					}
				});
	}
	
	public static boolean isBaseType(Class<?> className) {
		return BeanUtils.isBaseType(className);
	}
}
