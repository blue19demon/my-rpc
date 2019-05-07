package com.rpc.diyrpc.consumer;

public class Test {
	public static void main(String[] args) throws Exception {
		String test = "{databaseIp}:{databasePort}{instanceName};database";
		test=test.replaceAll("\\{databaseIp\\}","127.0.0.1").replaceAll("\\{databasePort\\}","3306").replaceAll("\\{instanceName\\}","mysql");
		System.out.println(test);
	}
}