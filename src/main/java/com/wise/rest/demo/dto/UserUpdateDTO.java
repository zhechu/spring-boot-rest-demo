package com.wise.rest.demo.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 用户更新传输类
 *
 * @author lingyuwang
 * @date 2020-08-01 10:09
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDTO {

    /**
     * 用户ID
     */
    Long userId;

    /**
     * 密码
     */
    String password;

    /**
     * 有效开始时间
     */
    Date termStartTime;

    /**
     * 有效结束时间
     */
    Date termEndTime;

    /**
     * 操作用户ID
     */
    Long operateUserId;

    /**
     * 最后更新时间
     */
    Date lastUpdateTime;

}
