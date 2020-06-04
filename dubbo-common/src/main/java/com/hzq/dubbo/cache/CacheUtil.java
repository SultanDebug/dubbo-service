package com.hzq.dubbo.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * 缓存操作
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/4 9:39
 */
@SuppressWarnings("unchecked")
@Component
public class CacheUtil {
    /**
     * 获取缓存时设置缓存
     *
     * @param supplier 未命中的操作
     * @param key 缓存key
     * @param type 反序列化类型
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:29
    */
     public <T> T getCache(Supplier<T> supplier,String key,Class<?> type){
        String s = getCache(key);
         System.out.println("获取缓存数据："+s);
        if(s==null || s.length()==0){
            System.out.println("直接调用获取数据");
            T t = supplier.get();
            //todo 添加缓存
            return t;
        }else{
            System.out.println("缓存命中");
            return (T) desObj(s,type);
        }
    }

    /**
     * 反序列化
     *
     * @param val 反序列化文本 type 反序列化类型
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:31
    */
    private Object desObj(String val , Class<?> type){
        if(type.equals(String.class)){
            return val;
        }else if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
            return Integer.valueOf(val);
        } else if (type.equals(Long.class) || type.equals(Long.TYPE)) {
            return Long.valueOf(val);
        } else if (type.equals(Byte.class) || type.equals(Byte.TYPE)) {
            return Byte.valueOf(val);
        } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
            return Double.valueOf(val);
        } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
            return Float.valueOf(val);
        } else if (type.equals(Short.class) || type.equals(Short.TYPE)) {
            return Short.valueOf(val);
        } else if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
            return Boolean.valueOf(val);
        } else{
            Object o = JSON.parse(val);
            if(o instanceof JSONArray){
                JSONArray jsonArray = (JSONArray) o;
                return jsonArray.toJavaList(type);
            }else if (o instanceof JSONObject){
                JSONObject object = (JSONObject) o;
                return object.toJavaObject(type);
            }
        }
        return val;
    }

    /**
     * 模拟缓存数据
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:31
    */
    private <T> T getCache(String key){
        int a = (int)(Math.random()*10)%2;
        if(a != 0){
            return null;
        }else{
            Object o = "{\"chName\":\"黄大狗\",\"dept\":\"黄大狗部门\",\"name\":\"sultan\"}";
            return (T) o;
        }
    }
}
