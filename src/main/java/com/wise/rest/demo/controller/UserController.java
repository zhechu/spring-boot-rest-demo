package com.wise.rest.demo.controller;

import com.wise.rest.demo.enums.YesNoEnum;
import com.wise.rest.demo.obj.dto.UserDeleteDTO;
import com.wise.rest.demo.obj.dto.UserUpdateDTO;
import com.wise.rest.demo.obj.dvo.UserCreateDVO;
import com.wise.rest.demo.obj.dvo.UserFindDVO;
import com.wise.rest.demo.obj.dvo.UserUpdateDVO;
import com.wise.rest.demo.obj.entity.UserDO;
import com.wise.rest.demo.obj.qo.UserQO;
import com.wise.rest.demo.obj.vo.Response;
import com.wise.rest.demo.obj.vo.UserLoginVO;
import com.wise.rest.demo.obj.vo.UserVO;
import com.wise.rest.demo.service.UserService;
import com.wise.rest.demo.util.UserContextUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
@Validated
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
     * 批量创建用户
     *
     * @param userCreateDVOList
     * @return com.wise.rest.demo.oo.vo.Response<java.lang.Long>
     * @author lingyuwang
     * @date 2020-08-01 11:12
     * @since 1.1.3.0
     */
    @PostMapping("/batch")
    public Response<List<String>> batchCreate(@RequestBody @NotEmpty(message = "用户不能为空") @Valid List<UserCreateDVO> userCreateDVOList) {
        // 获取操作用户
        UserLoginVO userLoginVO = UserContextUtil.getUserLoginVO();

        List<UserDO> userDOList = userCreateDVOList.stream().filter(Objects::nonNull).map(userCreateDVO -> {
            // 属性复制
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userCreateDVO, userDO);
            userDO.setOperateUserId(userLoginVO.getUserId());
            return userDO;
        }).collect(Collectors.toList());

        List<Long> userIdList = userService.batchCreate(userDOList);
        return Response.success(
                userIdList.stream()
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        );
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

    /**
     * 批量更新用户
     *
     * @param userUpdateDVOList
     * @return com.wise.rest.demo.obj.vo.Response<java.lang.Integer>
     * @author lingyuwang
     * @date 2020-08-01 15:41
     * @since 1.1.3.0
     */
    @PutMapping("/batch")
    public Response<Integer> batchUpdate(@RequestBody @NotEmpty(message = "用户不能为空") @Valid List<UserUpdateDVO> userUpdateDVOList) {
        // 获取操作用户
        UserLoginVO userLoginVO = UserContextUtil.getUserLoginVO();

        List<UserUpdateDTO> userUpdateDTOList = userUpdateDVOList.stream().filter(Objects::nonNull).map(userUpdateDVO -> {
            // 属性复制
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
            BeanUtils.copyProperties(userUpdateDVO, userUpdateDTO);
            userUpdateDTO.setOperateUserId(userLoginVO.getUserId());
            return userUpdateDTO;
        }).collect(Collectors.toList());

        return Response.success(userService.batchUpdate(userUpdateDTOList));
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return com.wise.rest.demo.obj.vo.Response<java.lang.Integer>
     * @author lingyuwang
     * @date 2020-08-01 14:32
     * @since 1.1.3.0
     */
    @DeleteMapping("/{userId}")
    public Response<Integer> delete(@PathVariable Long userId) {
        UserDeleteDTO userDeleteDTO = new UserDeleteDTO();
        userDeleteDTO.setUserId(userId);
        userDeleteDTO.setDelFlag(YesNoEnum.YES.getKey());

        // 获取操作用户
        UserLoginVO userLoginVO = UserContextUtil.getUserLoginVO();
        userDeleteDTO.setOperateUserId(userLoginVO.getUserId());

        return Response.success(userService.delete(userDeleteDTO));
    }

    /**
     * 获取用户
     *
     * @param userId
     * @return com.wise.rest.demo.obj.vo.Response<com.wise.rest.demo.obj.vo.UserVO>
     * @author lingyuwang
     * @date 2020-08-01 14:51
     * @since 1.1.3.0
     */
    @GetMapping("/{userId}")
    public Response<UserVO> findByUserId(@PathVariable Long userId) {
        return Response.success(userService.findByUserId(userId));
    }

    /**
     * 根据用户名获取用户
     *
     * @param userName
     * @return com.wise.rest.demo.obj.vo.Response<com.wise.rest.demo.obj.vo.UserVO>
     * @author lingyuwang
     * @date 2020-08-01 14:51
     * @since 1.1.3.0
     */
    @GetMapping("/userName/{userName}")
    public Response<UserVO> findByUserName(@PathVariable String userName) {
        return Response.success(userService.findByUserName(userName));
    }

    /**
     * 批量获取用户
     *
     * @param userFindDVO
     * @return com.wise.rest.demo.obj.vo.Response<java.util.List<com.wise.rest.demo.obj.vo.UserVO>>
     * @author lingyuwang
     * @date 2020-08-01 15:56
     * @since 1.1.3.0
     */
    @GetMapping
    public Response<List<UserVO>> find(@ModelAttribute @Validated UserFindDVO userFindDVO) {
        // 属性复制
        UserQO userQO = new UserQO();
        BeanUtils.copyProperties(userFindDVO, userQO);

        return Response.success(userService.find(userQO));
    }

}
