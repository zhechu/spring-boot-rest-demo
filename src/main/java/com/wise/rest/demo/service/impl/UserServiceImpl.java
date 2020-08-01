package com.wise.rest.demo.service.impl;


import com.wise.rest.demo.dao.UserDao;
import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author lingyuwang
 * @date 2020-08-01 11:01
 * @since 1.1.3.0
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Long create(UserDO userDO) {
        return userDao.insert(userDO);
    }

    @Override
    public int update(UserUpdateDTO userUpdateDTO) {
        return userDao.update(userUpdateDTO);
    }

}
