package com.hzq.dubbo;

import static org.junit.Assert.assertTrue;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hzq.dubbo.provider.ProviderInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 远程测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboConsumerApp.class)
@WebAppConfiguration
//@Ignore
public class ConsumerTest
{
    @Reference
    private ProviderInterface service;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Before
    public void before(){
        System.out.println("执行之前操作");
    }

    @After
    public void after(){
        System.out.println("执行之后操作");
    }

    @Test
    public void dubboTest(){
        System.out.println(service.remote("sultan"));
    }

}
