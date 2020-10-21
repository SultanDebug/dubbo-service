
package com.hzq.dubbo.interfaces;

/**
 * 字段值修改自定义
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/21 10:55
 */
public class MyProcess implements ProcessInterface{
    @Override
    public String run(String value) {
        return "my process "+value;
    }
}
