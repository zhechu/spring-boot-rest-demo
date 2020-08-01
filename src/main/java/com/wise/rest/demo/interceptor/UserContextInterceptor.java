package com.wise.rest.demo.interceptor;

import com.wise.rest.demo.util.UserContextUtil;
import com.wise.rest.demo.oo.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户上下文拦截器
 *
 * @author lingyuwang
 * @date 2020-08-01 10:24
 * @since 1.1.3.0
 */
@Slf4j
@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 模拟根据请求携带的用户身份信息，到缓存获取当前用户登录信息
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setUserId(1L);
        userLoginVO.setUserName("zhangsan");
        UserContextUtil.setUserLoginVO(userLoginVO);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContextUtil.removeUserLoginVO();
    }

}
