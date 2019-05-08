package com.rpc.diyrpc.provider.webservice.api;

import javax.jws.WebService;

@WebService
public interface PresonService {

	String queryWeather(String city);

	public APIResponse getPresonInfo(Preson preson);
}
