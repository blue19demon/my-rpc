package com.sean;  
  
import java.net.URI;  
import java.util.Iterator;  
  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.MultivaluedMap;  
import javax.ws.rs.core.UriBuilder;  
  
import com.sun.jersey.api.client.Client;  
import com.sun.jersey.api.client.ClientResponse;  
import com.sun.jersey.api.client.WebResource;  
import com.sun.jersey.api.client.config.ClientConfig;  
import com.sun.jersey.api.client.config.DefaultClientConfig;  
  
public class JerseyClient {  
  
    public static void main(String[] args) {  
//      要使用Jersey Client API，必须首先创建Client的实例  
//      有以下两种创建Client实例的方式  
          
//     方式一  
        ClientConfig cc = new DefaultClientConfig();  
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);  
//      Client实例很消耗系统资源，需要重用  
//      创建web资源，创建请求，接受响应都是线程安全的  
//      所以Client实例和WebResource实例可以在多个线程间安全的共享  
        Client client = Client.create(cc);  
          
//      方式二  
//      Client client = Client.create();  
//      client.setConnectTimeout(10*1000);  
//      client.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);  
          
//      WebResource将会继承Client中timeout的配置  
        WebResource resource = client.resource("http://127.0.0.1:10000/service/sean?desc=description");  
          
        String str = resource  
                .accept(MediaType.TEXT_PLAIN)  
                .type(MediaType.TEXT_PLAIN)  
                .get(String.class);  
        System.out.println("String:" + str);  
          
        URI uri = UriBuilder.fromUri("http://127.0.0.1/service/sean").port(10000)  
                .queryParam("desc", "description").build();  
        resource = client.resource(uri);  
          
        //header方法可用来添加HTTP头  
        ClientResponse response = resource.header("auth", "123456")  
                .accept(MediaType.TEXT_PLAIN)  
                .type(MediaType.TEXT_PLAIN)  
                .get(ClientResponse.class);  
//      将HTTP响应打印出来  
        System.out.println("****** HTTP response ******");  
        StringBuilder strBuilder = new StringBuilder();  
        strBuilder.append("HTTP/1.1 ");  
        strBuilder.append(response.getStatus() + " ");  
        strBuilder.append(response.getStatusInfo() + "[\\r\\n]");  
        System.out.println(strBuilder.toString());  
        MultivaluedMap<String, String> headers = response.getHeaders();  
        Iterator<String> iterator = headers.keySet().iterator();  
        while(iterator.hasNext()){  
            String headName = iterator.next();  
            System.out.println(headName + ":" + headers.get(headName) + "[\\r\\n]");  
        }  
        System.out.println("[\\r\\n]");  
        System.out.println(response.getEntity(String.class) + "[\\r\\n]");  
    }  
}