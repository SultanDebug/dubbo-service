
package com.hzq.dubbo.aop;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志参数透传
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/18 17:54
 */
@Data
public class LogTrace implements Serializable {
    private static final long serialVersionUID = -1713394248147217663L;

    private static final ThreadLocal<String> traceid = new InheritableThreadLocal();

    public static String getTraceid() {
        return traceid.get();
    }

    public static void setTraceid(String trace) {
        traceid.set(trace);
    }

    public static void removeTraceid() {
        traceid.remove();
    }

}
