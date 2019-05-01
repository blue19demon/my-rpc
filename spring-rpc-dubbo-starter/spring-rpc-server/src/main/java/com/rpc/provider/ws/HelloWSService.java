package com.rpc.provider.ws;

import com.rpc.api.HelloRemoteService;
import com.rpc.core.anno.RemoteService;

@RemoteService
public class HelloWSService implements HelloRemoteService {
	
	public String say(String name) {
		return "hello "+name;
	}

}
