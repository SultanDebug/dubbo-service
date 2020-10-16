
package com.hzq.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:52
 */
@Data
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1938732970897738104L;
    private String code;
    private String name;
    private String type;
    private PermissionDTO children;
}
