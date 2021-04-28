/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/27 14:40
 */
public abstract class AsynCallUtil<R> implements Runnable {

    protected Callable<R> callable;

    public AsynCallUtil(Callable<R> callable) {
        this.callable = callable;
    }

    public void run() {
        try {
            try {
                System.out.println("线程组"+Thread.currentThread().getThreadGroup().getName()+",线程"+Thread.currentThread().getName()+"运行");
                Thread.sleep(100);
                this.doCall();
            }finally {
                //清理线程变量
            }
        } catch (Exception e){
            throw new RuntimeException("运行异常："+e.getMessage());
        }
    }

    public abstract void doCall() throws Exception;

    public static<R> List<R> submit(ExecutorService service, Callable<R>... callables) throws Exception {
        List<R> list = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch count = new CountDownLatch(callables.length);
        for (Callable<R> callable : callables) {
            AsynCallUtil<R> call = new AsynCallUtil<R>(callable) {
                @Override
                public void doCall() throws Exception {
                    try {
                        R call1 = this.callable.call();
                        list.add(call1);
                    }finally {
                        count.countDown();
                    }
                }
            };
            service.execute(call);
        }
        count.await();
        return list;
    }
}
