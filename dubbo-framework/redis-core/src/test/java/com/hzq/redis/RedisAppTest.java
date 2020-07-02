package com.hzq.redis;

import static org.junit.Assert.assertTrue;

import com.hzq.redis.cache.CacheUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RedisAppTest
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
//        UserInfo s = cacheUtil.getCache(()->getUserInfo("测试"),"testKey",UserInfo.class);
//        System.out.println("结果："+s);
    }
}
