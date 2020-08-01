package com.wise.rest.demo.service;

import com.wise.rest.demo.obj.dto.UserDeleteDTO;
import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.obj.qo.UserQO;
import com.wise.rest.demo.obj.vo.UserVO;

import java.util.List;

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
     * 批量创建用户
     *
     * @param userDOList
     * @return java.util.List<java.lang.Long>
     * @author lingyuwang
     * @date 2020-08-01 15:11
     * @since 1.1.3.0
     */
    List<Long> batchCreate(List<UserDO> userDOList);

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

    /**
     * 批量更新用户
     *
     * @param userUpdateDTOList
     * @return int
     * @author lingyuwang
     * @date 2020-08-01 15:34
     * @since 1.1.3.0
     */
    int batchUpdate(List<UserUpdateDTO> userUpdateDTOList);

    /**
     * 删除用户
     *
     * @param userDeleteDTO
     * @return int
     * @author lingyuwang
     * @date 2020-08-01 14:28
     * @since 1.1.3.0
     */
    int delete(UserDeleteDTO userDeleteDTO);

    /**
     * 获取用户
     *
     * @param userId
     * @return com.wise.rest.demo.obj.vo.UserVO
     * @author lingyuwang
     * @date 2020-08-01 14:44
     * @since 1.1.3.0
     */
    UserVO findByUserId(Long userId);

    /**
     * 获取用户
     *
     * @param userName
     * @return com.wise.rest.demo.obj.vo.UserVO
     * @author lingyuwang
     * @date 2020-08-01 14:57
     * @since 1.1.3.0
     */
    UserVO findByUserName(String userName);

    /**
     * 批量获取用户
     *
     * @param userQO
     * @return java.util.List<com.wise.rest.demo.obj.vo.UserVO>
     * @author lingyuwang
     * @date 2020-08-01 15:52
     * @since 1.1.3.0
     */
    List<UserVO> find(UserQO userQO);

}
