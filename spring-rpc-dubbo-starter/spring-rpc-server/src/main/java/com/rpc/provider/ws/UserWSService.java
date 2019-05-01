package com.rpc.provider.ws;

import com.rpc.api.UserRemoteService;
import com.rpc.core.anno.RemoteService;
import com.rpc.vo.UserVO;

@RemoteService
public class UserWSService implements UserRemoteService {

	public UserVO save(UserVO user) {
		user.setUserId(1);
		return user;
	}
	
}
