
package com.rpc.diyrpc.provider.rest.api.impl;

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

import com.rpc.diyrpc.provider.rest.api.Department;
import com.rpc.diyrpc.provider.rest.api.DeptService;
import com.sun.jersey.spi.resource.Singleton;

@Path("/deptAPI")
@Singleton
public class DeptServiceImpl implements DeptService{

	@Path("/listJson")
	@GET
	/* 两种格式都返回，具体看客户端需要什么,默认就是返回xml
	 * @Produces(MediaType.APPLICATION_JSON)//返回数据消息类型
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)//接收数据消息类型
	 */
	@Override
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
	@Override
	public List<Department> listXML() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}

	@Path("/listAll")
	@GET
	@Override
	public List<Department> listAll() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public List<Department> list01() {
		List<Department> dept = new ArrayList<>();
		dept.add(new Department(1L, "dept1"));
		dept.add(new Department(2L, "dept2"));
		return dept;
	}
	
	@Path("/listGet")
	@GET
	@Override
	public Department list02(@QueryParam("id")String id,@QueryParam("name")String name) {
		return new Department(Long.parseLong(id), name);
	}

	@GET
	@Path("/{id}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Department get(@PathParam("id")Long id,@PathParam("name")String name) {
		return new Department(id, name);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)//返回数据消息类型
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//接收数据消息类型
	@Override
	public Department save(@FormParam("name") String name) {
		Department d = new Department(1L, name);
		return d;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
	public Department update(@PathParam("id") Long id, @FormParam("name") String name) {
		Department d = new Department(id, name);
		return d;
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public void delete(@PathParam("id") Long id) {
		System.out.println("删除部门：" + id);
	}

	@POST
	@Path("/saveMuti")
	@Produces(MediaType.APPLICATION_JSON)//返回数据消息类型
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//接收数据消息类型
	@Override
	public Department saveMuti(@FormParam("id")Long id,@FormParam("name")String name) {
		Department d = new Department(id, name);
		return d;
	}
}
