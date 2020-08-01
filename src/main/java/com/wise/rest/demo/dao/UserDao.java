package com.wise.rest.demo.dao;

import com.wise.rest.demo.oo.dto.UserUpdateDTO;
import com.wise.rest.demo.oo.entity.UserDO;
import com.wise.rest.demo.util.IdGeneratorUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户持久层
 *
 * @author lingyuwang
 * @date 2020-08-01 10:01
 * @since 1.1.3.0
 */
@Repository
public class UserDao {

    /**
     * 添加用户
     *
     * @param userDO
     * @return java.lang.Long
     * @author lingyuwang
     * @date 2020-08-01 11:07
     * @since 1.1.3.0
     */
    public Long insert(UserDO userDO) {
        // 生成用户ID
        Long userId = IdGeneratorUtil.generateId();
        userDO.setUserId(userId);

        // 填充创建时间和最后更新时间
        Date date = new Date();
        userDO.setCreateTime(date);
        userDO.setLastUpdateTime(date);

        // TODO 保存到数据库

        return userId;
    }

    /**
     * 更新用户
     *
     * @param userUpdateDTO
     * @return int
     * @author lingyuwang
     * @date 2020-08-01 11:46
     * @since 1.1.3.0
     */
    public int update(UserUpdateDTO userUpdateDTO) {
        // 填充最后更新时间
        Date date = new Date();
        userUpdateDTO.setLastUpdateTime(date);

        // TODO 更新到数据库

        return 1;
    }

}
