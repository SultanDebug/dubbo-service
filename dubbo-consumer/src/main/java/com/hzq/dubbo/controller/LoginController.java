
package com.hzq.dubbo.controller;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录接口
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/10 15:59
 */
@RestController
@Slf4j
public class LoginController {

    /**
     * token生成
     * @param name
     * @param chName
     * @param dept
     * @return
     */
    @PostMapping("/login")
    public ResultResponse<String> getToken(String name, String chName, String dept){
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
        return ResultResponse.success(JwtUtils.getToken(name,chName,dept));
    }

    /**
     * token有效性检查
     * @return
     */
    @GetMapping("/check")
    public ResultResponse<Boolean> checkToken(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String token = request.getHeader("Authorization");
        return ResultResponse.success(JwtUtils.checkToken(token));
    }
}
