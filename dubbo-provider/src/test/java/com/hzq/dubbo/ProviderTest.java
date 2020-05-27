package com.hzq.dubbo;

import static org.junit.Assert.assertTrue;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hzq.dubbo.provider.ProviderInterface;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboProviderApp.class)
@WebAppConfiguration
//@Ignore
public class ProviderTest
{


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

}
