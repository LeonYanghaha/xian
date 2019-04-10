package com.xian.demo.Interceptor;

import com.xian.demo.entity.User;
import com.xian.demo.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xian.demo.util.JWTTool;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        log.info("---------------------开始进入请求地址拦截----------------------------");
        //这里需要将optios请求放行了，才能执行后面的操作
        // 2019-3-27 13:38 今天在这里学到很多东西。
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", httpServletRequest.getMethod());
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }

        String token = httpServletRequest.getHeader(Common.getReqHeadKey());
        if(token == null || "".equals(token)){
            return false;
        }
        System.out.println(token);
        User user = JWTTool.unsign(token, User.class);
        if(user != null){
            httpServletRequest.setAttribute(Common.getReqUserKey(), user);
            // 进入到这里的，都是成功登录的，需要重新计算token，并写入header
            String newToken = JWTTool.sign(user,60L* 1000L* 30L);
            System.out.println(newToken);
            httpServletResponse.setHeader(Common.getReqUserKey(), newToken);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------视图渲染之后的操作-------------------------0");
    }
}
