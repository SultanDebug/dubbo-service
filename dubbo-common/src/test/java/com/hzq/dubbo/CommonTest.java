package com.hzq.dubbo;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.cache.CacheUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CommonTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * 测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:32
    */
    @Test
    public void testCache(){
        CacheUtil cacheUtil = new CacheUtil();
        UserInfo s = cacheUtil.getCache(()->getUserInfo("测试"),"testKey",UserInfo.class);
        System.out.println("结果："+s);
    }

    /**
     * 模拟缓存未命中的字符串类型
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:31
    */
    private String getValue(String para){
        return "db获取数据："+para;
    }

    /**
     * 模拟缓存未命中的pojo类型
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:32
    */
    private UserInfo getUserInfo(String para){
        String s = "{\"chName\":\"黄大狗1\",\"dept\":\"黄大狗部门1\",\"name\":\"sultan1\"}";
        return JSONObject.parseObject(s,UserInfo.class);
    }
}
