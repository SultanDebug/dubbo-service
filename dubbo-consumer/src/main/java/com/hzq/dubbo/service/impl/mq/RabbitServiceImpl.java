
package com.hzq.dubbo.service.impl.mq;

import com.hzq.dubbo.User;
import com.hzq.dubbo.mapper.UserMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/5 15:35
 */
@Service
public class RabbitServiceImpl implements RabbitService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 直接的发布消息
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public User publishMsg(Integer id) {
        User user = userMapper.getUser(id);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("ex.hzq","route.hzq",user,correlationData);
        return user;
    }
}
