package com.hzq.dubbo.aop;

import com.hzq.dubbo.jwt.JwtUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局token参数透传
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 17:26
 */
@Data
public class UserInfo implements Serializable {
    private static final ThreadLocal<String> user = new InheritableThreadLocal();
    private static final long serialVersionUID = -4999416659510988123L;

    private String name;

    private String chName;

    private String dept;

    public static String getUser() {
        return user.get();
    }

    public static void setUser(String user) {
        UserInfo.user.set(user);
    }

    public static UserInfo getUserInfo() {
        return JwtUtils.toObj(user.get());
    }

    public static void setAdminUser() {
        UserInfo.user.set(JwtUtils.getToken("admin","管理员","管理部门"));
    }

    public static void removeUser() {
        UserInfo.user.remove();
    }
}
