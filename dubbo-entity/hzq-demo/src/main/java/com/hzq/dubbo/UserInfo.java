package com.hzq.dubbo;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 自动代码插件  事务测试实体
 *
 * @author huangzq
 * @since 2020-07-24
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 职务
     */
    private String userPosition;

    /**
     * 关系
     */
    private String userRelationShip;

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
