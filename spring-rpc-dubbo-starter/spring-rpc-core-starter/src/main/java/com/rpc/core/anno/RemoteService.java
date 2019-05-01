package com.rpc.core.anno;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 *
 * 使用这个标签的实现类，将会在注册一个由spring管理的rmi服务，服务名就是其指定的接口名称。服务端口号是由RmiServiceProperty注释指定
 */
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RemoteService {

}