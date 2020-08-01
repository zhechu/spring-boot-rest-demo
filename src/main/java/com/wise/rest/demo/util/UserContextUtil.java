package com.wise.rest.demo.util;

import com.wise.rest.demo.obj.vo.UserLoginVO;

/**
 * 用户上下文信息
 *
 * @author lingyuwang
 * @date 2020-08-01 10:20
 * @since 1.1.3.0
 */
public class UserContextUtil {

    private final static ThreadLocal<UserLoginVO> contextThreadLocal = new ThreadLocal<>();

    private UserContextUtil() {}

    public static void setUserLoginVO(UserLoginVO userLoginVO) {
        contextThreadLocal.set(userLoginVO);
    }

    /**
     * 获取当前登录用户
     */
    public static UserLoginVO getUserLoginVO() {
        return contextThreadLocal.get();
    }

    /**
     * 手动移除
     */
    public static void removeUserLoginVO() {
        contextThreadLocal.remove();
    }

}
