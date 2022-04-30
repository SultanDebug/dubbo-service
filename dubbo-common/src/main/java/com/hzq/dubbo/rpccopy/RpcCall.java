package com.hzq.dubbo.rpccopy;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * @Author: Huangzq
 * @Date: 2022/4/30 17:18
 */
public class RpcCall {
    public static String call(String url , String port , Method method , Object[] args){
        System.out.println("start call=====>");
        String res = url+"=>" + port+"=>" + method.getName()+"=>" + JSONArray.toJSONString(args);
        System.out.println("end call=====>");
        return res;
    }
}
