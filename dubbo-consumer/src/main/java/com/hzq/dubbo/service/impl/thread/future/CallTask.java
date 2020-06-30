
package com.hzq.dubbo.service.impl.thread.future;

import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/30 11:38
 */
public class CallTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("线程开始计算");
        TimeUnit.SECONDS.sleep(3);
        Integer s = 0 ;
        for (int i = 0; i < 100; i++) {
            s+=i;
        }
        System.out.println("线程结束计算");
        return s;
    }
}
