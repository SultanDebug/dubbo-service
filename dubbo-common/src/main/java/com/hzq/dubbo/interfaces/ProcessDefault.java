
package com.hzq.dubbo.interfaces;

import org.springframework.stereotype.Component;

/**
 * 字段值处理默认方式
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/21 10:55
 */
@Component("defaultProcess")
public class ProcessDefault implements ProcessInterface{
    @Override
    public String run(String value) {
        return "default "+value;
    }
}
