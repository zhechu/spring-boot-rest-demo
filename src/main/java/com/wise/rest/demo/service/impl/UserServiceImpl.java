package com.wise.rest.demo.service.impl;


import com.wise.rest.demo.dao.UserDao;
import com.wise.rest.demo.obj.dto.UserDeleteDTO;
import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.obj.vo.UserVO;
import com.wise.rest.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Long create(UserDO userDO) {
        log.info("创建用户:{}", userDO);

        return userDao.insert(userDO);
    }

    @Override
    public int update(UserUpdateDTO userUpdateDTO) {
        log.info("更新用户:{}", userUpdateDTO);

        return userDao.update(userUpdateDTO);
    }

    @Override
    public int delete(UserDeleteDTO userDeleteDTO) {
        log.info("删除用户:{}", userDeleteDTO);

        // TODO 调用持久化层进行逻辑删除
        return 1;
    }

    @Override
    public UserVO findByUserId(Long userId) {
        log.info("获取用户:{}", userId);

        UserDO userDO = userDao.selectByUserId(userId);

        // TODO 聚合用户信息

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }

}
