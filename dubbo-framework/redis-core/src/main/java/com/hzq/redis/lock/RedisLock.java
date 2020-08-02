package com.hzq.redis.lock;

import java.lang.annotation.*;

/**
 * 分布式锁注解
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/2 10:44
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    String key();
    int expire() default 30000;
    int timeout() default 10000;
}
