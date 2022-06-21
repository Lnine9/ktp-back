package com.ktp.main.config.intercepter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ktp.main.config.exception.TransException;
import com.ktp.main.config.exception.TransExceptionCode;
import com.ktp.main.entity.User;
import com.ktp.main.service.UserService;
import com.ktp.main.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, Object object) {

        String token = httpServletRequest.getHeader("auth-token");
        String userId;
        String username;
        if (token == null){
            throw new TransException(TransExceptionCode.AUTH_FAIL);
        }
        try {
            userId = JwtUtil.getUserId(token);
            username = JwtUtil.getUsername(token);
            User user = userService.getById(userId);
            if (user == null || !user.getUsername().equals(username)){
                throw new TransException(TransExceptionCode.AUTH_FAIL);
            }
            JwtUtil.verify(token);
        } catch (TokenExpiredException e) {
            throw new TransException(TransExceptionCode.AUTH_TIME_OUT);
        } catch (Exception e) {
            throw new TransException(TransExceptionCode.AUTH_FAIL);
        }

        httpServletRequest.setAttribute("userId", userId);
        httpServletRequest.setAttribute("username", username);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
