package com.rpc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rpc.api.HelloRemoteService;
import com.rpc.api.UserRemoteService;
import com.rpc.constomer.ConstomerApplication;
import com.rpc.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={ConstomerApplication.class})
public class RmiCostomerTester {

	@Autowired
	private HelloRemoteService helloRemoteService;
	@Autowired
	private UserRemoteService userRemoteService;
	@Test
	public void test() {
		System.out.println(helloRemoteService.say("张三年"));
		UserVO user=new UserVO().setUserName("張三丰").setPassword("111").setPhone("1828737448");
		System.out.println(userRemoteService.save(user));
	}
	
}
