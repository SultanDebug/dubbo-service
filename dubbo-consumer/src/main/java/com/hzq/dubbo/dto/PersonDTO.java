
package com.hzq.dubbo.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/7 16:59
 */
@Data
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = -8857414801250914139L;
    private String name;
    private Integer age;
    private List<String> role;
}
