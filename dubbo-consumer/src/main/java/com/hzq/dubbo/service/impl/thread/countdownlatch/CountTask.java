
package com.hzq.dubbo.service.impl.thread.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/30 14:26
 */
public class CountTask implements Callable<Integer> {
    private CountDownLatch latch;

    private Integer opt;

    public CountTask(CountDownLatch latch , Integer opt) {
        this.latch = latch;
        this.opt = opt;
    }

    public void exec(){

        try {
            System.out.println("开始执行任务"+opt);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("结束执行任务"+opt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("线程开始计算");
        TimeUnit.SECONDS.sleep(3);
        Integer s = 0 ;
        for (int i = 0; i < 10+opt; i++) {
            s+=i;
        }
        System.out.println("线程结束计算");
        return s;
    }
}
