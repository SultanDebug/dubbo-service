
package com.hzq.dubbo.config;

import io.shardingsphere.core.keygen.KeyGenerator;

/**
 * 分布式id算法
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/12 17:45
 */
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Number generateKey() {
        return null;
    }
}
