/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/17 11:41
 */
public interface CacheEventService<T> {
    void publishEvent(T t);
}
