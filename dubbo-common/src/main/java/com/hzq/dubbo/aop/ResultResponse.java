package com.hzq.dubbo.aop;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局返回类型
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/1 11:53
 */
@Data
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = 9064214930247718458L;
    private String code;
    private String msg;
    private T data;

    public static ResultResponse success(){
        ResultResponse res = new ResultResponse();
        res.setCode("200");
        return res;
    }

    public static ResultResponse success(Object data){
        ResultResponse res = new ResultResponse();
        res.setCode("200");
        res.setData(data);
        return res;
    }

    public static ResultResponse fail(String code ,String msg){
        ResultResponse res = new ResultResponse();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
}
