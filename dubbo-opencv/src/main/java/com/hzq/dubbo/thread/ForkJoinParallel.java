/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.thread;

import com.hzq.dubbo.aop.UserInfo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/4 17:00
 */
public class ForkJoinParallel {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /*public static void main(String[] args) {
        parallelThread();
    }*/

    public static void parallelThread(){
        List<UserInfo> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            UserInfo userInfo  = new UserInfo();
            userInfo.setChName(i+"");
            userInfo.setName(i+"");
            list.add(userInfo);
        }

        /*并行流*/
        UserInfo.setUser("hzq");
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());
        list.parallelStream().forEach(o->{
            //UserInfo.setUser(Thread.currentThread().getId()+"");
            System.out.println("并行流线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());
        });
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());

        /*普通线程*/
        UserInfo.setUser("sultan");
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());
        for (int i=0;i<10;i++){
            int k = i;
            Thread thread = new Thread(()->{
                //UserInfo.setUser(k+"");
                System.out.println("thread线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());
            });
            thread.start();
        }
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+UserInfo.getUser());

        /*普通类型线程变量*/
        threadLocal.set("sultan");
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+threadLocal.get());
        for (int i=0;i<10;i++){
            int k = i;
            Thread thread = new Thread(()->{
                //UserInfo.setUser(k+"");
                System.out.println("thread线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+threadLocal.get());
            });
            thread.start();
        }
        System.out.println("main线程信息：id："+Thread.currentThread().getId()+"--name："+Thread.currentThread().getName()+"--user:"+threadLocal.get());

    }

    //并发测试
    public static void parallelTest() {
        List<UserInfo> list = new ArrayList<>();

        for (int i=0;i<1000;i++){
            UserInfo userInfo  = new UserInfo();
            userInfo.setChName(i+"");
            userInfo.setName(i+"");
            list.add(userInfo);
        }

        List<UserInfo> listAdd = new ArrayList<>();
        List<UserInfo> listSync = Collections.synchronizedList(listAdd);

        System.out.println("线程开始>>>>>>>");
        list.parallelStream().forEach(o->{
//            Integer i = Integer.parseInt(o.getName())+1;
//            o.setName(o.getChName());
            listSync.add(o);
        });

        System.out.println("线程结束>>>>>>>");
        for (UserInfo userInfo : listSync) {
            if(userInfo == null
                    || StringUtils.isEmpty(userInfo.getName())
                    || userInfo.getName()==null
                    || userInfo.getChName()==null
                    || !userInfo.getName().equals(userInfo.getChName())){
                System.out.println("线程异常"+userInfo);
            }
        }

    }



}
