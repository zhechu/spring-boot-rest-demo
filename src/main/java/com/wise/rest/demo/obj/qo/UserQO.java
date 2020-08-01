package com.wise.rest.demo.obj.qo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 用户查询传输类
 *
 * @author lingyuwang
 * @date 2020-08-01 10:09
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQO {

    /**
     * 用户名
     */
    String userName;

    /**
     * 有效开始时间
     */
    Date termStartTime;

    /**
     * 有效结束时间
     */
    Date termEndTime;

}
