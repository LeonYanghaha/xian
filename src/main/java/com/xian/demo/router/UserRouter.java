package com.xian.demo.router;

import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.UserService;
import com.xian.demo.service.impl.ProductServiceImpl;
import com.xian.demo.util.Common;
import com.xian.demo.util.JWTTool;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user/")
public class UserRouter {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTTool jwtTool;
    @Autowired
    private Common common;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @RequestMapping(value = "changeHeadImage",method = RequestMethod.POST)
    public Result changeHeadImage(HttpServletRequest request){
        return  Result.ok("ok");
    }

    /**
     * @describe  登录
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(@RequestParam(value = "un") String un,
                        @RequestParam(value = "pw") String pw,
                        HttpServletRequest request){
        User user = userService.login(un,pw);
        if(user==null){
            return Result.errorMsg("用户名或密码错误");
        }else{
            String token = JWTTool.sign(user,60L* 1000L* 30L);
            if(token == null){
                return Result.errorMsg("未知错误");
            }else{
                user.setPhone(token);
                return Result.ok(user);
            }
        }
    }
    /**
     * @describe 注册路由
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result register (@Validated User user,
                            BindingResult bindingResult,
                            @RequestParam String repw) {

        logger.info("register--" +  new Date().toString() + user.toString());
        if(!repw.equals(user.getPw())){
            return  Result.errorMsg("两次输入的密码不一致");
        }
        if(bindingResult.hasErrors()){
            return Result.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }

        if(!userService.checkUn(user.getUn())){
            return  Result.errorMsg("用户名已经被使用");
        }
        user.setRegisterTime(new Date());
        Integer status = userService.register(user);
        if(status == 1){
            return Result.ok(user);
        }else{
            return Result.errorMsg("未知错误");
        }
    }

    /**
     * @describe 检查用户名是否被使用
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "checkUn",method = RequestMethod.POST)
    public Result checkUn(@Param(value = "un") String un){
        if(userService.checkUn(un)){
            return  Result.ok("ok");
        }else{
            return Result.errorMsg("用户名已经被使用");
        }
    }
}
