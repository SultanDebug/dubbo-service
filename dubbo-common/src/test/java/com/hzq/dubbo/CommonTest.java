package com.hzq.dubbo;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.rpccopy.MyReference;
import com.hzq.dubbo.rpccopy.MyReferenceInjectProc;
import com.hzq.dubbo.rpccopy.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonTest.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@ImportResource()
//@MapperScan("")
@Import({MyReferenceInjectProc.class})
public class CommonTest
{
    @MyReference(host = "127.0.0.1",port = "9999")
    private TestService testService;


    @Before
    public void before(){
        /*when(testService.test1(any())).thenReturn("asd");*/
    }

    @Test
    public void contextLoads() {
        String s = testService.test1("asdddasd");
        System.out.println(s);
    }
}
