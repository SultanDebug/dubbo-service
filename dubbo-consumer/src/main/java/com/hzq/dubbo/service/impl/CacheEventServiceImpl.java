
package com.hzq.dubbo.service.impl;

import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.service.CacheEventService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/17 11:43
 */
@Service
public class CacheEventServiceImpl implements CacheEventService<UserInfo> , ApplicationContextAware {
    private ApplicationContext context;
    @Override
    public void publishEvent(UserInfo userInfo) {
        context.publishEvent(userInfo);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
