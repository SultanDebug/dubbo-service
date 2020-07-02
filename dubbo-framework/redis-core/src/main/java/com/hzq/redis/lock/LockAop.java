/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/2 10:44
 */
@Component
@Aspect
@Slf4j
public class LockAop {
    public static final DefaultRedisScript REMOVE_LOCK_LUA_SCRIPT = new DefaultRedisScript("if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return -1 end", Long.class);
    public static final DefaultRedisScript GET_LOCK_LUA_SCRIPT = new DefaultRedisScript("if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end", Long.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("@annotation(com.hzq.redis.lock.RedisLock)")
    public void proccess(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        RedisLock annotation = method.getAnnotation(RedisLock.class);

        //todo 锁定代码  finally释放锁
    }

}
