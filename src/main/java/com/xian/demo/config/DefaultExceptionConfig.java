package com.xian.demo.config;

import com.xian.demo.entity.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DefaultExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        return Result.errorException(e.getMessage());
    }
}
