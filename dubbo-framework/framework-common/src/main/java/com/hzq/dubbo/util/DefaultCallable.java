/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.util;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/28 12:05
 */
@Data
public class DefaultCallable implements Callable<Integer> {
    private Integer a;
    private Integer b;

    public DefaultCallable(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        return a+b;
    }
}
