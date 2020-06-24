/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.bussinessutil;

import com.hzq.dubbo.service.BussinessBaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/24 17:39
 */
public class BussinessUtils {
    private static Map<Integer, BussinessBaseService> container = new HashMap<>();

    public static void add(Integer key,BussinessBaseService val){
        container.put(key,val);
    }

    public static BussinessBaseService get(Integer key){
        return container.get(key);
    }
}
