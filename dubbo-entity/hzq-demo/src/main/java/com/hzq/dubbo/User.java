
package com.hzq.dubbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzq.dubbo.annotation.CommonProcess;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 自动代码插件  事务测试实体
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:17
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8870610904728644216L;
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 更新人
     */
    private String updateName;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDate;
}
