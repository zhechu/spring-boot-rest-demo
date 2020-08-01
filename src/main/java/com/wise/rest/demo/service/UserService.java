package com.wise.rest.demo.service;

import com.wise.rest.demo.dto.UserUpdateDTO;
import com.wise.rest.demo.entity.UserDO;

public interface UserService {

    /**
     * 创建用户
     *
     * @param userDO
     * @return java.lang.Long
     * @author lingyuwang
     * @date 2020-08-01 10:56
     * @since 1.1.3.0
     */
    Long create(UserDO userDO);

    /**
     * 更新用户
     *
     * @param userUpdateDTO
     * @return java.lang.Integer
     * @author lingyuwang
     * @date 2020-08-01 11:44
     * @since 1.1.3.0
     */
    int update(UserUpdateDTO userUpdateDTO);

}
