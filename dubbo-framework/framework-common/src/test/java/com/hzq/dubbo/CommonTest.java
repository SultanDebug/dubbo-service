package com.hzq.dubbo;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.util.AsynCallUtil;
import com.hzq.dubbo.util.DefaultCallable;
import com.hzq.dubbo.util.DefaultThreadFactory;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.concurrent.*;

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

    @Test
    public void call(){
        Callable<Integer>[] callables = new Callable[10];
        for (int i = 0; i < 10; i++) {
            DefaultCallable callable = new DefaultCallable(i,i);
            callables[i] = callable;
        }
        ExecutorService service = new ThreadPoolExecutor(
            10,20,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<>()
                ,new DefaultThreadFactory("MyTest"),new ThreadPoolExecutor.CallerRunsPolicy()
        );

        try {
            List<Integer> submit = AsynCallUtil.submit(service, callables);

            for (int i = 0; i < submit.size(); i++) {
                System.out.println(submit.get(i));
            }

            service.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void copy(){
        CopyDto copyDto = new CopyDto();
        copyDto.setI(3);
        CopyVo vo = new CopyVo();
        vo.setVo("111");
        copyDto.setVo(vo);

        /*CopyDto copy = new CopyDto();
        BeanUtils.copyProperties(copyDto,copy);*/

        CopyDto copy = null;


        copy.getVo().setVo("222");

        System.out.println(copyDto);
    }
}
