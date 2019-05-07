package com.rpc.diyrpc.protocol.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;

public class RedissonClientBuilder {
	private static RedissonClient redisson;
	public static RedissonClient build() {
		if(redisson==null) {
			// 创建配置
			Config config = new Config();
			Configure conf=RPCConfigure.getConfigure();
			// 指定使用单节点部署方式
			config.useSingleServer().setAddress("redis://"+conf.getRedisHostname()+":"+conf.getRedisPort());
			// 创建客户端(发现创建RedissonClient非常耗时，基本在2秒-4秒左右)
			redisson = Redisson.create(config);
			return redisson;
		}
		return redisson;
	}
}
