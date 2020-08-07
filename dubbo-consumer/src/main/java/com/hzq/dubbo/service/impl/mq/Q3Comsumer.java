/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service.impl.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/5 15:38
 */
//@Component
//@RabbitListener(queues = "queue.hzq")
@Slf4j
public class Q3Comsumer /*implements ChannelAwareMessageListener*/ {
//    @RabbitHandler
    public void proccess(User user){
        log.info("Q3:"+JSONObject.toJSONString(user));
    }

//    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("Q3:"+JSONObject.toJSONString(message));
    }

//    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        log.info("确认机制：{}",JSONObject.toJSONString(mode));
    }
}
