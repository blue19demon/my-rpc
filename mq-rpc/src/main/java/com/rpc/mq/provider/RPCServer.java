package com.rpc.mq.provider;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rpc.mq.anno.RemoteService;
import com.rpc.mq.core.Invocation;
import com.rpc.mq.registry.Registry;
import com.rpc.mq.tools.BeanUtils;
import com.rpc.mq.tools.JavaClazzUtils;
import com.rpc.mq.tools.URL; 
//RPC调用服务端  
@SuppressWarnings("deprecation")
public class RPCServer {  
    private static final String RPC_QUEUE_NAME = "rpc_queue_service";  
    private static final String BASE_REMOTE_API_PACAKAGE = "com.rpc.mq.api,com.rpc.mq.api2";  
    public static void main(String[] args) throws Exception {  
        //• 先建立连接、通道，并声明队列  
    	long  start=System.currentTimeMillis();
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("127.0.0.1");  
        factory.setUsername("eric");  
        factory.setPassword("123456");  
        factory.setPort(AMQP.PROTOCOL.PORT);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);  
        //•可以运行多个服务器进程。通过channel.basicQos设置prefetchCount属性可将负载平均分配到多台服务器上。  
        channel.basicQos(1);  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        //打开应答机制autoAck=false  
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);  
       
        //注册服务
        URL url=new URL("127.0.0.1", AMQP.PROTOCOL.PORT);
        startScannerRemoteService(url);
        System.out.println("RPC Server started in "+(System.currentTimeMillis()-start)/1000.0+" seconds");  
        while (true) {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            BasicProperties props = delivery.getProperties();  
            BasicProperties replyProps = new BasicProperties.Builder()  
                    .correlationId(props.getCorrelationId()).build();  
            Invocation invocation = (Invocation) BeanUtils.ByteToObject(delivery.getBody());
			Class<?> inplClass=Registry.get(invocation.getInterfaceName(), url);
			Method method=inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
			Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
            //返回处理结果队列  
            channel.basicPublish("", props.getReplyTo(), replyProps,  
                    BeanUtils.ObjectToByte(result));  
            //发送应答   
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  
        }  
    }
	/**
	 * 扫描注解，注册到容器
	 * @param url
	 */
	private static void startScannerRemoteService(URL url) {
		if(!"".equals(BASE_REMOTE_API_PACAKAGE)) {
			Set<Class<?>> classSets=new HashSet<>();
			String[] packageList=BASE_REMOTE_API_PACAKAGE.split(",");
			for (int i = 0; i < packageList.length; i++) {
				classSets.addAll(JavaClazzUtils.getClasses(packageList[i]));
			}
			if(classSets.size()>0) {
				for (Class<?> clazz : classSets) {
					if(clazz.isAnnotationPresent(RemoteService.class)) {
						Class<?> value=clazz.getAnnotation(RemoteService.class).value();
						if(value!=null) {
							Registry.register(value, url, clazz);
						}
					}
				}
			}
		}
	}  
}  