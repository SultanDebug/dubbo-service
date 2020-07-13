package com.hzq.dubbo.user.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangzq
 * @since 2020-07-07
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Date updateDate;

}
