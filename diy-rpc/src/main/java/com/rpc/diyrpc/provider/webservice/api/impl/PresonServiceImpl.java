package com.rpc.diyrpc.provider.webservice.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.provider.webservice.api.APIResponse;
import com.rpc.diyrpc.provider.webservice.api.Preson;
import com.rpc.diyrpc.provider.webservice.api.PresonService;


public class PresonServiceImpl implements PresonService {

	@Override
	public String queryWeather(String city) {
		return city+" today is 35℃";
	}

	@Override
	public APIResponse getPresonInfo(Preson preson) {
		return APIResponse.builder().data(JSONObject.toJSONString(preson)).timpstamp(String.valueOf(System.currentTimeMillis())).build();
	}
	
}