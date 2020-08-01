package com.wise.rest.demo.obj.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 用户实体（对应数据库表）
 *
 * @author lingyuwang
 * @date 2020-08-01 10:02
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDO {

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

    /**
     * 删除标识(0：未删除 1：已删除)
     */
    Integer delFlag;

}
