package net.cc.service;

import javax.jws.WebService;

import net.cc.domain.APIResponse;
import net.cc.domain.APIRquest;

@WebService
public interface APIDemo {
	 public APIResponse call(APIRquest request);
}
