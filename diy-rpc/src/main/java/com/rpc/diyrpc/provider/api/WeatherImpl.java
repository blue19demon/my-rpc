package com.rpc.diyrpc.provider.api;

import javax.jws.WebService;

@WebService(serviceName = "weather")
public class WeatherImpl implements Weather {
	public String queryWeather(String city) {
		return city + " 今日天气为晴，偏北风二到三级";
	}

	@Override
	public Order getMyInfo(Order order) {
		if (null == order) {
			return new Order();
		}
		System.out.println(order);
		order.setAge(99);
		return order;
	}
}