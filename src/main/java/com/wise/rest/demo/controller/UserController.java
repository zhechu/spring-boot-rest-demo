package com.wise.rest.demo.controller;

import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.dvo.UserCreateDVO;
import com.wise.rest.demo.obj.dvo.UserUpdateDVO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.obj.vo.Response;
import com.wise.rest.demo.obj.vo.UserLoginVO;
import com.wise.rest.demo.service.UserService;
import com.wise.rest.demo.util.UserContextUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author lingyuwang
 * @date 2020-08-01 9:58
 * @since 1.1.3.0
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 创建用户
     *
     * @param userCreateDVO
     * @return com.wise.rest.demo.oo.vo.Response<java.lang.Long>
     * @author lingyuwang
     * @date 2020-08-01 11:12
     * @since 1.1.3.0
     */
    @PostMapping
    public Response<String> create(@RequestBody @Validated UserCreateDVO userCreateDVO) {
        // 属性复制
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userCreateDVO, userDO);

        // 获取操作用户
        UserLoginVO userLoginVO = UserContextUtil.getUserLoginVO();
        userDO.setOperateUserId(userLoginVO.getUserId());

        Long userId = userService.create(userDO);
        return Response.success(String.valueOf(userId));
    }

    /**
     * 更新用户
     *
     * @param userUpdateDVO
     * @return com.wise.rest.demo.oo.vo.Response<java.lang.Integer>
     * @author lingyuwang
     * @date 2020-08-01 11:45
     * @since 1.1.3.0
     */
    @PutMapping
    public Response<Integer> update(@RequestBody @Validated UserUpdateDVO userUpdateDVO) {
        // 属性复制
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(userUpdateDVO, userUpdateDTO);

        // 获取操作用户
        UserLoginVO userLoginVO = UserContextUtil.getUserLoginVO();
        userUpdateDTO.setOperateUserId(userLoginVO.getUserId());

        return Response.success(userService.update(userUpdateDTO));
    }

}
