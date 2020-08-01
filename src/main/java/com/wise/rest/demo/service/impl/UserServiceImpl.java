package com.wise.rest.demo.service.impl;


import com.google.common.collect.Lists;
import com.wise.rest.demo.dao.UserDao;
import com.wise.rest.demo.obj.dto.UserDeleteDTO;
import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.obj.qo.UserQO;
import com.wise.rest.demo.obj.vo.UserVO;
import com.wise.rest.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<Long> batchCreate(List<UserDO> userDOList) {
        log.info("批量创建用户:{}", userDOList);

        return userDOList.stream()
                .filter(Objects::nonNull)
                .map(userDO -> userDao.insert(userDO))
                .collect(Collectors.toList());
    }

    @Override
    public int update(UserUpdateDTO userUpdateDTO) {
        log.info("更新用户:{}", userUpdateDTO);

        return userDao.update(userUpdateDTO);
    }

    @Override
    public int batchUpdate(List<UserUpdateDTO> userUpdateDTOList) {
        log.info("批量更新用户:{}", userUpdateDTOList);

        return userUpdateDTOList.stream()
                .filter(Objects::nonNull)
                .mapToInt(userUpdateDTO -> userDao.update(userUpdateDTO))
                .sum();
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

    @Override
    public UserVO findByUserName(String userName) {
        log.info("获取用户:{}", userName);

        UserDO userDO = userDao.selectByUserName(userName);

        // TODO 聚合用户信息

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> find(UserQO userQO) {
        log.info("批量获取用户:{}", userQO);

        // 模拟数据库返回
        List<UserVO> userVOList = Lists.newArrayListWithExpectedSize(3);
        for (int i = 0; i < 3; i++) {
            UserDO userDO = new UserDO();
            userDO.setUserId(1L);
            userDO.setUserName("zhangsan");
            userDO.setPassword("123456");
            userDO.setTermStartTime(new Date());
            userDO.setTermEndTime(new Date());
            userDO.setCreateTime(new Date());
            userDO.setLastUpdateTime(new Date());
            userDO.setOperateUserId(1L);

            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDO, userVO);

            userVOList.add(userVO);
        }

        return userVOList;
    }

}
