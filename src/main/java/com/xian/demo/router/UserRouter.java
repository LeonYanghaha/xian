package com.xian.demo.router;

import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("user/")
public class UserRouter {

    @Autowired
    private UserService userService;
    private Result result;

    @RequestMapping(value = "changeHeadImage",method = RequestMethod.POST)
    public Result changeHeadImage(HttpServletRequest request){
//        String str = userService.changeHeadImage();
        result =  Result.ok("ok");
        return result;
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
        result = new Result();
        if(user==null){
            return Result.errorMsg("用户名或密码错误");
        }else{
            request.getSession().setAttribute("currentUser", user);
            return Result.ok(user);
        }
    }
    /**
     * @describe 注册路由
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result register (@Validated User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return Result.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }

        result = new Result();

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
        result = new Result();
        if(userService.checkUn(un)){
            return  Result.ok("ok");
        }else{
            return Result.errorMsg("用户名已经被使用");
        }
    }
}
