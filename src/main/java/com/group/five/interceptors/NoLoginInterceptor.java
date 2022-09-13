package com.group.five.interceptors;


import com.group.five.exceptions.NoLoginException;
import com.group.five.service.UserService;
import com.group.five.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        if(null == userId ||null == userService.selectByPrimaryKey(userId)){
            throw new NoLoginException();
        }
        return true;
    }

}
