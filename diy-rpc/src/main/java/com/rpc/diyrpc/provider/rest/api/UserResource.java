
package com.rpc.diyrpc.provider.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Path("/users")
@Singleton
public class UserResource {
	private static Map<String, User> userMap = new HashMap<String, User>();// �洢�û�

	/**
	 * 返回多节点XML格式文件
	 * 
	 * @return
	 */

	@GET
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})  
    @Produces(MediaType.TEXT_PLAIN)  
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		User u1 = new User("001", "HuiJia", "26");
		User u2 = new User("002", "Andy", "23");
		User u3 = new User("003", "BoWen", "21");

		userMap.put(u1.getUserId(), u1);
		userMap.put(u2.getUserId(), u2);
		userMap.put(u3.getUserId(), u3);

		users.addAll(userMap.values());
		return users;
	}

	/**
	 * 返回单节点XML格式文件
	 * 
	 * @return
	 */

	@GET
	@Path("/getUserXml")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})  
    @Produces(MediaType.TEXT_PLAIN)  
	public User getUserXml() {
		User user = new User();
		user.setAge("21");
		user.setUserId("004");
		user.setName("回嘉");
		return user;
	}

	/**
	 * 返回单节点Json格式文件
	 * 
	 * @return
	 */
	@GET

	@Path("/getUserJson")

	@Produces(MediaType.APPLICATION_JSON)
	public User getUserJson() {
		User user = new User();
		user.setAge("30");
		user.setUserId("2");
		user.setName("回嘉");
		return user;
	}
}
