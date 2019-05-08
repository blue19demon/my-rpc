package com.rpc.diyrpc.provider.rest.api;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.rpc.diyrpc.protocol.restful.RestfulService;

@RestfulService
@Path("/orderAPI")
public interface OrderService {

	@GET
	@Path("/listGet")
	public List<Order> list(@QueryParam("price") BigDecimal price);

}
