
package com.hzq.dubbo.spi;

import com.hzq.dubbo.aop.ResultResponse;

/**
 * spi注册bean 业务2
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/2 17:23
 */
public class Woman implements Person {
    @Override
    public ResultResponse<String> getMsg(String para) {
        return ResultResponse.success("hey I'm woman , "+para);
    }
}
