package com.rpc.constomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rpc.core.anno.EnableAutoRemoteLookup;

@SpringBootApplication
@EnableAutoRemoteLookup
public class ConstomerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConstomerApplication.class, args);
	}

}
