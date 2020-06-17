/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service.impl.listen;

import com.hzq.dubbo.aop.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/17 11:48
 */
@Service
@Slf4j
public class UserInfoHandler {
    @EventListener
    public void handler(UserInfo userInfo){
        log.info("收到事件{}",userInfo);
    }
}
