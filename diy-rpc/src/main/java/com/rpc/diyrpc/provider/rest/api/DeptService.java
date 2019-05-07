package com.rpc.diyrpc.provider.rest.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.rpc.diyrpc.protocol.restful.RestfulService;

@RestfulService
@Path("/deptAPI")
public interface DeptService {

	@Path("/listJson")
	@GET
	public List<Department> list();

	@Path("/listXML")
	@GET
	public List<Department> listXML();

	@Path("/listAll")
	@GET
	public List<Department> listAll();

	@Path("/list")
	@GET
	public List<Department> list01();

	@GET
	@Path("/listGet")
	public Department list02(@QueryParam("id") String id, @QueryParam("name") String name);

	@Path("/{id}/{name}")
	@GET
	public Department get(@PathParam("id") Long id, @PathParam("name") String name);

	@POST
	public Department save(@FormParam("name") String name);

	@Path("/saveMuti")
	@POST
	public Department saveMuti(@FormParam("id") Long id, @FormParam("name") String name);

	@PUT
	@Path("/{id}")
	public Department update(@PathParam("id") Long id, @FormParam("name") String name);

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id);

}
