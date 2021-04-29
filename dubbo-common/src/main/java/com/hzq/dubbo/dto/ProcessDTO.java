
package com.hzq.dubbo.dto;

import com.hzq.dubbo.annotation.CommonProcess;
import com.hzq.dubbo.interfaces.MyProcess;
import lombok.Data;

/**
 * 字段注解式修改
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/21 11:04
 */
@Data
public class ProcessDTO {
    private String id;

    @CommonProcess(clazz = MyProcess.class,beanName = "myProcess")
    private String name;

    private String age;
}
