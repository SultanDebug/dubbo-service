
package com.hzq.dubbo.service.impl.listen;

import com.hzq.dubbo.aop.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * spring事件机制
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/17 11:48
 */
@Service
@Slf4j
public class UserInfoHandler {
    
    /**
     * TODO
     * 
     * @param 
     * @return 
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:25
    */
    @EventListener
    public void handler(UserInfo userInfo){
        log.info("收到事件{}",userInfo);
    }
}
