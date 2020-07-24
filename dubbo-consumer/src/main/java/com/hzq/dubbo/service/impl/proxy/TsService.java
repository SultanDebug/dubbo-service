/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service.impl.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/24 16:28
 */
@Service
@Slf4j
public class TsService {

    public void mid(){
        /**
         * 方法内部调用，代理不生效
         * 对应@transaction 内部调用注解标识方法并不生效
         * 只有走代理对象，aop才生效
         */
        this.methodOne();
        this.methodTwo();
    }

    @InTransfer
    public void methodOne(){
        log.info("方法One执行");
    }

    @InTransfer
    public void methodTwo(){
        log.info("方法Two执行");
    }
}
