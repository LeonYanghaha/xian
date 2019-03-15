package com.xian.demo.router;

import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.UserService;
import com.xian.demo.util.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequestMapping("user/")
public class UserRouter {

    @Autowired
    private UserService userService;
    private Result result;

    @RequestMapping(value = "changeHeadImage",method = RequestMethod.POST)
    public Result changeHeadImage(){
        String str = userService.changeHeadImage();
        result =  Result.ok(str);
        return result;
    }

    /**
     * @describe  登录
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(@RequestParam(value = "un") String un,
                        @RequestParam(value = "pw") String pw){

        User user = userService.login(un,pw);
        result = new Result();
        if(user==null){
            return Result.errorMsg("用户名或密码错误");
        }else{
            return Result.ok(user);
        }
    }
    /**
     * @describe 注册路由
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result register(@RequestParam(value = "un") String un,
                           @RequestParam(value = "pw") String pw,
                           @RequestParam(value = "phone") String phone){

        result = new Result();
        if(!userService.checkUn(un)){
            return  Result.errorMsg("用户名已经被使用");
        }
        User user = new User();
        user.setPw(pw);
        user.setUn(un);
        user.setPhone(phone);
        user.setHeadImage("0.jpg");
        user.setId(Common.getUserId());
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
        result = new Result();
        if(userService.checkUn(un)){
            return  Result.ok("ok");
        }else{
            return Result.errorMsg("用户名已经被使用");
        }
    }
}
