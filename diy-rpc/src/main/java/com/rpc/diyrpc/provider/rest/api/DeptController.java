
package com.rpc.diyrpc.provider.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Path("/deptAPI")
@Singleton
public class DeptController {

	@Path("/listJson")
	@GET
	/* 两种格式都返回，具体看客户端需要什么,默认就是返回xml
	 * @Produces(MediaType.APPLICATION_JSON)//返回数据消息类型
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)//接收数据消息类型
	 */
	public List<Department> list() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}

	@Path("/listXML")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public List<Department> listXML() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}

	@Path("/listAll")
	@GET
	public List<Department> listAll() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}

	@Path("/list")
	@GET // 1
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_XML)
	public List<Department> list01() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}
	
	@Path("/listGet")
	@GET // 1
	public Department list02(@QueryParam("id")String id,@QueryParam("name")String name) {
		return new Department(Long.parseLong(id), name);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_XML)
	public Department get(@PathParam("id") Long id) {
		return new Department(id, "dept2");
	}

	@POST // 2
	@Produces(MediaType.APPLICATION_JSON)//返回数据消息类型
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//接收数据消息类型
	public Department save(@FormParam("name") String name) {
		Department d = new Department(1L, name);
		return d;
	}

	@PUT // 3
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Department update(@PathParam("id") Long id, @FormParam("name") String name) {
		Department d = new Department(id, name);
		return d;
	}

	@DELETE // 4
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void delete(@PathParam("id") Long id) {
		System.out.println("删除部门：" + id);
	}
}
