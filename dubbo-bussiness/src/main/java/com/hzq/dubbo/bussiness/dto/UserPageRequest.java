
package com.hzq.dubbo.bussiness.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/5/6 17:07
 */
@Data
public class UserPageRequest {
    private Integer curr;
    private Integer size;
}
