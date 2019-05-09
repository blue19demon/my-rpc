package com.rpc.diyrpc.protocol.mq;

import java.lang.reflect.Method;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rpc.diyrpc.framework.BeanUtils;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.ZKRegister;

@SuppressWarnings("deprecation")
public class MQServer {
	private static final String RPC_QUEUE_NAME = "rpc_queue_service";  
	public void start(String hostname, Integer port) {
		try {
			//• 先建立连接、通道，并声明队列  
			Configure conf = RPCConfigure.getConfigure();
			ConnectionFactory factory = new ConnectionFactory();  
			factory.setHost(conf.getMqHostname());  
			factory.setUsername(conf.getMqUsername());  
			factory.setPassword(conf.getMqPassword());  
			factory.setPort(conf.getMqPort()==null?AMQP.PROTOCOL.PORT:conf.getMqPort());  
			Connection connection = factory.newConnection();  
			Channel channel = connection.createChannel();  
			channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);  
			//•可以运行多个服务器进程。通过channel.basicQos设置prefetchCount属性可将负载平均分配到多台服务器上。  
			channel.basicQos(1);  
			QueueingConsumer consumer = new QueueingConsumer(channel);  
			//打开应答机制autoAck=false  
			channel.basicConsume(RPC_QUEUE_NAME, false, consumer);  
			//注册服务
			URL url=new URL(conf.getHostname(), conf.getPort());
			System.out.println("MQ started");
			while (true) {  
			    QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
			    BasicProperties props = delivery.getProperties();  
			    BasicProperties replyProps = new BasicProperties.Builder()  
			            .correlationId(props.getCorrelationId()).build();  
				Invocation invocation = (Invocation) BeanUtils.ByteToObject(delivery.getBody());
				Class<?> inplClass = ZKRegister.get(invocation.getInterfaceName(), url);
				Method method = inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
				Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
				// 返回处理结果队列
				channel.basicPublish("", props.getReplyTo(), replyProps, BeanUtils.ObjectToByte(result));
				// 发送应答
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
		

	}
}
