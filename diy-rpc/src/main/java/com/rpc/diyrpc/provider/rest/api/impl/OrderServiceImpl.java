package com.rpc.diyrpc.provider.rest.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.rpc.diyrpc.provider.rest.api.Order;
import com.rpc.diyrpc.provider.rest.api.OrderService;
import com.sun.jersey.spi.resource.Singleton;

@Path("/orderAPI")
@Singleton
public class OrderServiceImpl implements OrderService {

	@Override
	@Path("/listGet")
	@GET
	public List<Order> list(@QueryParam("price") BigDecimal price) {
		return Arrays.asList(
				new Order(UUID.randomUUID().toString().replaceAll("-", ""), price.setScale(2, RoundingMode.DOWN)),
				new Order(UUID.randomUUID().toString().replaceAll("-", ""), price.setScale(2, RoundingMode.DOWN)),
				new Order(UUID.randomUUID().toString().replaceAll("-", ""), price.setScale(2, RoundingMode.DOWN)));
	}

}
