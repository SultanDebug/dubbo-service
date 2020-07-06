/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 17:09
 */
public class MyLog implements Log {
    Logger log;
    public MyLog(String clazz) {
        log = Logger.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable throwable) {
        log.info("error:["+s+"]");
        log.info(throwable.getMessage());
    }

    @Override
    public void error(String s) {
        log.info("error:["+s+"]");
    }

    @Override
    public void debug(String s) {
        log.info("debug:["+s+"]");
    }

    @Override
    public void trace(String s) {
        log.info("trace:["+s+"]");
    }

    @Override
    public void warn(String s) {
        log.info("warn:["+s+"]");
    }
}
