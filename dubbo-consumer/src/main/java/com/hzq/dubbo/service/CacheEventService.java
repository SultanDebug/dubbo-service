
package com.hzq.dubbo.service;

/**
 * spring事件机制
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/17 11:41
 */
public interface CacheEventService<T> {
    void publishEvent(T t);
}
