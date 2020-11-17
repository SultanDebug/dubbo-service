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
