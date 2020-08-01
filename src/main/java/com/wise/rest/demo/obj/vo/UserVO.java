package com.wise.rest.demo.obj.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 用户返回类
 *
 * @author lingyuwang
 * @date 2020-08-01 10:08
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVO {

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    Long userId;

    /**
     * 用户名
     */
    String userName;

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
     * 创建时间
     */
    Date createTime;

    /**
     * 最后更新时间
     */
    Date lastUpdateTime;

}
