
package com.hzq.dubbo.controller;

import com.hzq.dubbo.User;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.service.TempService;
import com.hzq.dubbo.service.impl.mq.RabbitService;
import com.hzq.redis.cache.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 中间件测试
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/10 15:59
 */
@RestController
@Slf4j
public class MiddleWareController {
    @Resource
    private RedisTemplate<String, UserInfo> redisTemplate;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private TempService tempService;

    /**
     * redis包装测试
     * 包装一个获取方法  先读缓存再穿透更新缓存
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public ResultResponse<UserInfo> redis(String key){
//        UserInfo userInfo = UserInfo.getUserInfo();

        UserInfo cache = cacheUtil.getCache(tempService::getUser, key, UserInfo.class, 30L);

        log.info("拿到数据："+cache);
        /*ValueOperations<String, UserInfo> ops = redisTemplate.opsForValue();
        ops.set("key-1",userInfo);*/

        Boolean aBoolean = redisTemplate.hasKey(key);
        log.info("redis存在{}数据：{}",key,aBoolean);

//        UserInfo userInfo1 = ops.get("key-1");

        return ResultResponse.success(cache);
    }

    /**
     * 发布mq消息
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/5 15:37
     */
    @GetMapping("/publishMsg")
    public ResultResponse<User> publishMsg(Integer id){
        return ResultResponse.success(rabbitService.publishMsg(id));
    }

}
