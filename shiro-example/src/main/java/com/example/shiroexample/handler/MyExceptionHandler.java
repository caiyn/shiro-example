package com.example.shiroexample.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public String errorHandler(AuthorizationException e) {
        logger.error("没有通过权限验证！");
        return "没有通过权限验证！";
    }
}
