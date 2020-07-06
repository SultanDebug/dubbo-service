/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:17
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8870610904728644216L;
    private Integer id;

    private String user_name;

    private String sex;

    private Integer age;

    private String address;
}
