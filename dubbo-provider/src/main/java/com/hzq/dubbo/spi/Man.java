/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.spi;

import com.hzq.dubbo.aop.ResultResponse;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/2 17:21
 */
public class Man implements Person {
    @Override
    public ResultResponse<String> getMsg(String para) {
        return ResultResponse.success("hey I'm man , "+para);
    }
}
