# my-rpc
netty,http,jetty,socket,rmi,hessian,redis,webservice,restful,thrift等协议，实现自己的RPC框架

#### 步骤1. 
#### server和client添加依赖项
    <dependency>
      <groupId>com.rpc</groupId>
      <artifactId>spring-rpc-core-starter</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
#### 步骤2.项目中的使用
#### API 项目中定义接口
	public interface HelloRemoteService {
		public String say(String name);
	}
#### server 项目实现接口，添加注解
	@RemoteService
	public class HelloWSService implements HelloRemoteService {
		public String say(String name) {
			return "hello "+name;
		}
	}
##### application.yml上下文配置文件中新增如下内容，配置服务端暴露接口信息（目前版本只支持放在classpath路径下的文件名一致的文件）
    server:
      port: 80
    rpc:
      port: 1009
      #netty或者http、jetty
      protocol: http
      remoteClientScan:
        basePackages: com.rpc.api
      zookeeper:
        address: 127.0.0.1
        port: 2181
#### client 项目主启动类上新增注解
	@SpringBootApplication
	@EnableAutoRemoteLookup
	public class CostomerApplication {
		public static void main(String[] args) {
			SpringApplication.run(CostomerApplication.class, args);
		} 
	}
##### application.yml上下文配置文件中新增如下内容，配置客户端lookup接口信息（目前版本只支持放在classpath路径下的文件名一致的文件）
    server:
      port: 80
    rpc:
      port: 1009
      #netty或者http、jetty
      protocol: http
      remoteClientScan:
        basePackages: com.rpc.api
      zookeeper:
        address: 127.0.0.1
        port: 2181
#### 启动provider项目，发布RMI接口
#### costomer调用
	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringBootTest(classes={CostomerApplication.class})
	public class RmiCostomerTester {
		@Autowired
		private HelloRemoteService helloRemoteService;
		@Test
		public void test() {
			System.out.println(helloRemoteService.say("张三年"));
		}
	}
#### 远程调用接口success！！！
