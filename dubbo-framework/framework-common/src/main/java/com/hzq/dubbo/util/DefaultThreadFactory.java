/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/28 12:14
 */
public class DefaultThreadFactory implements ThreadFactory {
    private String name;

    private ThreadGroup group;

    private AtomicInteger tNum=  new AtomicInteger(1);

    public DefaultThreadFactory(String name) {
        SecurityManager var1 = System.getSecurityManager();
        this.name = name;
        this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group,r,name+tNum.getAndIncrement(),0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }

        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
