/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.config.jsckson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.hzq.dubbo.annotation.CommonProcess;
import com.hzq.dubbo.context.ApplicationContextUtils;
import com.hzq.dubbo.interfaces.ProcessInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/30 10:51
 */
@Slf4j
public class MyJacksonSerialize extends JsonSerializer<Object> implements ContextualSerializer {

    private CommonProcess commonProcess;

    public MyJacksonSerialize() {
    }

    MyJacksonSerialize(CommonProcess commonProcess){
        this.commonProcess = commonProcess;
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String val = (String) o;
        if (commonProcess!=null){
            ProcessInterface bean = ApplicationContextUtils.getApplicationContext().getBean(commonProcess.beanName(),ProcessInterface.class);
            val = bean.run((String) val);
        }
        jsonGenerator.writeString(val);
    }

    /**
     * 只执行一次  需要特别注意指针  否则有意想不到的结果
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/4/30 17:00
    */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer res = this;
        CommonProcess commonProcess = (CommonProcess)beanProperty.getAnnotation(CommonProcess.class);
        if (commonProcess != null) {
            // 获得注解上的值并赋值
            res = new MyJacksonSerialize(commonProcess);
        }
        return res;
    }
}
