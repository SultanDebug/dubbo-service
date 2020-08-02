
package com.hzq.dubbo;

import com.hzq.dubbo.service.impl.thread.countdownlatch.CountTask;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 并发测试
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/30 11:37
 */
public class FutureLatchTest {
    @Test
    public void test() {
        /**
         * callable-future
         */
        //线程池形式
        /*ExecutorService service = Executors.newFixedThreadPool(1);
        CallTask callTask = new CallTask();
        System.out.println("线程池提交任务");
        Future<Integer> result = service.submit(callTask);
        System.out.println("线程池结束任务");
        service.shutdown();*/

        //单纯线程形式
        /*CallTask callTask = new CallTask();
        FutureTask<Integer> result = new FutureTask<>(callTask);
        Thread thread = new Thread(result);
        System.out.println("线程池提交任务");
        thread.start();
        System.out.println("线程池结束任务");

        try {
            if(result.get()!=null){
                System.out.println("task运行结果"+result.get());
            }else{
                System.out.println("未获取到结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**
         * countdownlatch
         */
        CountDownLatch count = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            CountTask countTask = new CountTask(count,i);
            //runnable形式
            Thread thread = new Thread(countTask::exec);
            thread.start();
        }

        try {
            System.out.println("main开始");
            count.await();
            System.out.println("main结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
