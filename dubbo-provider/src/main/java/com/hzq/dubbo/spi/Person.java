
package com.hzq.dubbo.spi;

import com.hzq.dubbo.aop.ResultResponse;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/2 17:20
 */
public interface Person {
    ResultResponse<String> getMsg(String para);
}
