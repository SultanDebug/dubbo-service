
package com.hzq.dubbo.service.impl.mq;

import com.hzq.dubbo.User;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/5 15:34
 */
public interface RabbitService {
    User publishMsg(Integer id);
}
