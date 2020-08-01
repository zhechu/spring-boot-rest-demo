package com.wise.rest.demo.oo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 用户登录信息
 *
 * @author lingyuwang
 * @date 2020-08-01 10:08
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginVO {

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    Long userId;

    /**
     * 用户名
     */
    String userName;

}
