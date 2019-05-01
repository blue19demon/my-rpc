package com.rpc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rpc.core.anno.EnableRPCServer;

@SpringBootApplication
@EnableRPCServer
public class ProviderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

}
