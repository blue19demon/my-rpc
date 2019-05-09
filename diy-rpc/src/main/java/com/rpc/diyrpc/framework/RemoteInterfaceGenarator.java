package com.rpc.diyrpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rpc.diyrpc.protocol.redis.RedissonClientBuilder;
import com.rpc.diyrpc.protocol.restful.RestfulService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("deprecation")
public class RemoteInterfaceGenarator {

	@SuppressWarnings("unchecked")
	public static <T> T genarate(Class<? extends T> interfaceClass) {
		Configure conf = RPCConfigure.getConfigure();
		if (ProviderProtocol.MQ.equals(conf.getProtocol())) {
			return getProxyForMQ(conf, interfaceClass);
		} if (ProviderProtocol.WEBSERVICE.equals(conf.getProtocol())) {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(interfaceClass);
			factory.setAddress("http://" + conf.getHostname() + ":" + conf.getPort() + "/" + interfaceClass.getName());
			T client = (T) factory.create();
			return client;
		} else if (ProviderProtocol.RESTFUL.equals(conf.getProtocol())) {
			return getProxyForRestful(conf, interfaceClass);
		} else if (ProviderProtocol.REDIS.equals(conf.getProtocol())) {
			RedissonClient redisson = RedissonClientBuilder.build();
			RRemoteService remoteService = redisson.getRemoteService();
			Object target = remoteService.get(interfaceClass);
			return (T) target;
		} else if (ProviderProtocol.HESSIAN.equals(conf.getProtocol())) {
			HessianProxyFactory factory = new HessianProxyFactory();
			Object target = null;
			try {
				target = factory.create(interfaceClass,
						"http://" + conf.getHostname() + ":" + conf.getPort() + "/" + interfaceClass.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) target;
		} else if (ProviderProtocol.RMI.equals(conf.getProtocol())) {
			Object target = null;
			try {
				target = Naming
						.lookup("rmi://" + conf.getHostname() + ":" + conf.getPort() + "/" + interfaceClass.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) target;
		} else {
			return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							Protocol client = ProtocolFactory.getProtocol(conf.getProtocol());
							Invocation invocation = new Invocation();
							invocation.setInterfaceName(interfaceClass.getName());
							invocation.setMethodName(method.getName());
							invocation.setParams(args);
							invocation.setParamTypes(method.getParameterTypes());
							URL url = new URL(conf.getHostname(), conf.getPort());
							Object rs = client.post(url, invocation);
							return rs;
						}
					});
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T getProxyForMQ(Configure conf, Class<? extends T> interfaceClass) {
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
						factory.setHost(conf.getMqHostname());  
						factory.setUsername(conf.getMqUsername());  
						factory.setPassword(conf.getMqPassword());  
						factory.setPort(conf.getMqPort()==null?AMQP.PROTOCOL.PORT:conf.getMqPort());    
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

	@SuppressWarnings("unchecked")
	private static <T> T getProxyForRestful(Configure conf, Class<? extends T> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (interfaceClass.isAnnotationPresent(RestfulService.class)) {
							String serverHost = "http://" + conf.getHostname() + ":" + conf.getPort();
							String apiPath = serverHost;
							if (interfaceClass.isAnnotationPresent(Path.class)) {
								apiPath += interfaceClass.getAnnotation(Path.class).value();
							}
							if (method.isAnnotationPresent(Path.class)) {
								apiPath += method.getAnnotation(Path.class).value();
							}

							Parameter[] parameters = method.getParameters();
							Class<?> rt = method.getReturnType();
							MultivaluedMap<String, String> queryParams = null;
							if (parameters != null && parameters.length > 0 && args.length > 0) {
								queryParams = new MultivaluedMapImpl();
								for (int i = 0; i < parameters.length; i++) {
									if (parameters[i].isAnnotationPresent(QueryParam.class)) {
										queryParams.add(parameters[i].getAnnotation(QueryParam.class).value(),
												args[i] != null ? String.valueOf(args[i]) : null);
									}
									if (parameters[i].isAnnotationPresent(FormParam.class)) {
										queryParams.add(parameters[i].getAnnotation(FormParam.class).value(),
												args[i] != null ? String.valueOf(args[i]) : null);
									}
									if (parameters[i].isAnnotationPresent(PathParam.class)) {
										String paramValue = args[i] != null ? String.valueOf(args[i]) : null;
										String pathName = parameters[i].getAnnotation(PathParam.class).value();
										apiPath = apiPath.replaceAll("\\{" + pathName + "\\}", paramValue);
									}
								}
							}
							ClientConfig cc = new DefaultClientConfig();
							cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10 * 1000);
							Client client = Client.create(cc);
							WebResource webResource = client.resource(apiPath);
							if (method.isAnnotationPresent(GET.class)) {
								Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
								;
								if (queryParams != null) {
									builder = webResource.queryParams(queryParams).accept(MediaType.APPLICATION_JSON);
								}
								if (!rt.equals(void.class)) {
									String json = builder.get(String.class);
									if (!isBaseType(rt)) {
										if (rt == java.util.List.class) {
											Type genericType = method.getGenericReturnType();
											if (genericType instanceof ParameterizedType) {
												Type[] typesto = ((ParameterizedType) genericType)
														.getActualTypeArguments();
												JSONArray jsonArray = JSONArray.parseArray(json);
												List<Object> os = new ArrayList<>();
												for (Object object : jsonArray) {
													String jsonObject = JSONObject.toJSONString(object);
													os.add(JSONObject.parseObject(jsonObject, typesto[0]));
												}
												return os;
											}
										} else {
											return JSONObject.parseObject(json, rt);
										}
									}
									return json;
								}
								builder.get(void.class);
							} else if (method.isAnnotationPresent(POST.class)) {
								ClientResponse response = webResource.post(ClientResponse.class);
								if (queryParams != null) {
									response = webResource.post(ClientResponse.class, queryParams);
								}
								if (!rt.equals(void.class)) {
									String json = response.getEntity(String.class);
									if (!isBaseType(rt)) {
										return JSONObject.parseObject(json, rt);
									}
									return json;
								}
							} else if (method.isAnnotationPresent(PUT.class)) {
								ClientResponse response = webResource.put(ClientResponse.class);
								if (queryParams != null) {
									response = webResource.put(ClientResponse.class, queryParams);
								}
								if (!rt.equals(void.class)) {
									String json = response.getEntity(String.class);
									if (!isBaseType(rt)) {
										return JSONObject.parseObject(json, rt);
									}
									return json;
								}
							} else if (method.isAnnotationPresent(DELETE.class)) {
								ClientResponse response = webResource.delete(ClientResponse.class);
								if (queryParams != null) {
									response = webResource.post(ClientResponse.class, queryParams);
								}
								if (!rt.equals(void.class)) {
									String json = response.getEntity(String.class);
									if (!isBaseType(rt)) {
										return JSONObject.parseObject(json, rt);
									}
									return json;
								}
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
