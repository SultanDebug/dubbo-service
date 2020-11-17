
package com.hzq.dubbo.service;

import com.hzq.dubbo.annotation.CommonProcess;
import com.hzq.dubbo.interfaces.ProcessDefault;
import com.hzq.dubbo.interfaces.ProcessInterface;

import java.lang.reflect.Field;

/**
 * dto字段值处理主逻辑
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/21 11:06
 */
public class ProcessService {
    public static void process(Object o) throws IllegalAccessException, InstantiationException {
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            if(declaredField.isAnnotationPresent(CommonProcess.class)){
                CommonProcess annotation = declaredField.getAnnotation(CommonProcess.class);
                Class<? extends ProcessInterface> clazz = annotation.clazz();
                if(clazz == null){
                    clazz = ProcessDefault.class;
                }
                ProcessInterface processInterface = clazz.newInstance();
                declaredField.setAccessible(true);
                declaredField.set(o,processInterface.run((String) declaredField.get(o)));
            }
        }
    }
}
