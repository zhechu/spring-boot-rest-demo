package com.wise.rest.demo.obj.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 用户删除传输类
 *
 * @author lingyuwang
 * @date 2020-08-01 10:09
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDeleteDTO {

    /**
     * 用户ID
     */
    Long userId;

    /**
     * 操作用户ID
     */
    Long operateUserId;

    /**
     * 最后更新时间
     */
    Date lastUpdateTime;

    /**
     * 删除标识(0：未删除 1：已删除)
     */
    Integer delFlag;

}
