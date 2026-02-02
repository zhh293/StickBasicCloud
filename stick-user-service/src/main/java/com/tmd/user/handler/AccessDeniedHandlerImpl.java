package com.tmd.user.handler;

import com.alibaba.fastjson.JSON;

import com.tmd.common.domain.Result;
import com.tmd.common.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

    }
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        Result objectResult = Result.success(HttpStatus.FORBIDDEN.value());
//        String jsonString = JSON.toJSONString(objectResult);
//        //处理异常
//        WebUtils.renderString(httpServletResponse,jsonString );
//    }


}
