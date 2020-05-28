/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.aop;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 17:26
 */
public class UserInfo {
    private static final ThreadLocal<String> user = new InheritableThreadLocal();

    public static String getUser() {
        return user.get();
    }

    public static void setUser(String user) {
        UserInfo.user.set(user);
    }

    public static void removeUser() {
        UserInfo.user.remove();
    }
}
