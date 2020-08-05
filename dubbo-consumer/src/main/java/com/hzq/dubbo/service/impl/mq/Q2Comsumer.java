/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service.impl.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/5 15:38
 */
@Component
@RabbitListener(queues = "queue.hzq")
@Slf4j
public class Q2Comsumer {
    @RabbitHandler
    public void proccess(User user){
        log.info("Q2:"+JSONObject.toJSONString(user));
    }
}
