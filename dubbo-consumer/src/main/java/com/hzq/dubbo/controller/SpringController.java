
package com.hzq.dubbo.controller;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.bussinessutil.BussinessUtils;
import com.hzq.dubbo.config.Person;
import com.hzq.dubbo.dto.PersonDTO;
import com.hzq.dubbo.dto.TempDto;
import com.hzq.dubbo.service.CacheEventService;
import com.hzq.dubbo.service.ConfigService;
import com.hzq.dubbo.websocket.WebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring相关测试
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/10 15:59
 */
@RestController
@Slf4j
public class SpringController {
    @Autowired
    private CacheEventService<UserInfo> cacheEventService;

    @Autowired
    private ConfigService configService;

    /**
     * spring事件测试
     * @return
     */
    @GetMapping("/event")
    public ResultResponse<UserInfo> event(){
        UserInfo userInfo = UserInfo.getUserInfo();
        cacheEventService.publishEvent(userInfo);
        return ResultResponse.success(userInfo);
    }

    /**
     * 适配器测试
     * @param type
     * @param para
     * @return
     */
    @GetMapping("/adapt")
    public ResultResponse<String> adapt(Integer type , String para){
        String demo = BussinessUtils.get(type).demo(para);
        return ResultResponse.success(demo);
    }

    /**
     * 并发测试 无效
     * @param para
     * @return
     */
    @GetMapping("/concur")
    public ResultResponse<Integer> concur(Integer para){
        TempDto tempDto = new TempDto();
        Integer a = tempDto.getConCur();
//        val.getAndAdd(para);

        Integer b = a + para;
        tempDto.setConCur(b);
        if(!tempDto.getConCur().equals(b)){
            log.error("获取数据：{}",tempDto.getConCur());
        }
        return ResultResponse.success(a);
    }

    /**
     * sockect测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:10
     */
    @GetMapping("/socket")
    public ResultResponse<String> socket(String sid,String msg){
        WebsocketServer.sendInfo(msg,sid);
        return ResultResponse.success("成功");
    }

    /**
     * 获取配置
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/5 15:37
     */
    @GetMapping("/person")
    public ResultResponse<PersonDTO> person(){
        Person person = configService.getPerson();
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(person,personDTO);
        return ResultResponse.success(personDTO);
    }
}
