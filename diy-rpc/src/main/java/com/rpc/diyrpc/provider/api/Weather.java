package com.rpc.diyrpc.provider.api;

import javax.jws.WebService;

@WebService
public interface Weather {
    String queryWeather(String city);
    
    public Order getMyInfo(Order order);
}
