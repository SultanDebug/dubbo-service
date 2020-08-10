
package com.hzq.dubbo;

import com.hzq.dubbo.service.impl.thread.countdownlatch.CountTask;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CountDownLatch;

/**
 * 并发测试
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/30 11:37
 */
public class FutureLatchTest {
    private BigDecimal getRate(Integer canSale,Integer totle){
        if (canSale.equals(0)||totle.equals(0))return new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP);
        if(canSale>=totle)return new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);

        Integer saled = totle - canSale;
        BigDecimal bSale = new BigDecimal(saled);
        BigDecimal bTotle = new BigDecimal(totle);

        BigDecimal rate =bSale.divide(bTotle,2,RoundingMode.HALF_UP);

        return rate;

    }
    @Test
    public void decimalTest(){
        /*BigDecimal b = new BigDecimal(10.9865);
        System.out.println(b.setScale(2, RoundingMode.HALF_UP).toString()
                +"/"+b.setScale(2, RoundingMode.HALF_UP).toPlainString()
                +"/"+b.setScale(2, RoundingMode.HALF_UP).toEngineeringString());*/

        System.out.println(getRate(10,10));;
    }
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
