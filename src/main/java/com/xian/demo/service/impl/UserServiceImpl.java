package com.xian.demo.service.impl;

import com.xian.demo.dao.UserMapper;
import com.xian.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.xian.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser() {
        List<User> list = userMapper.findAll();
        return list;
    }

    public Boolean checkUn(String un){
        User user = userMapper.checkUn(un);
        if(user == null){
            return true;
        }else{
            return false;
        }
    }

    public User login(String un, String pw){
        User user = userMapper.login(un,pw);
        if(user == null){// 登录失败的情况
            return null;
        }
        Integer status = userMapper.updateLastLoginTime(new Date(), user.getId());
        if(status != 1){
            System.out.println("最后登录时间更新失败。。。。时间：" + new Date() + "ID:" + user.getId());
        }
        return user;
    }
    public Integer register(User user){
        return userMapper.register(user.getId(),user.getUn(),user.getPhone(),user.getPw(),user.getHeadImage(),user.getRegisterTime());
    }
    public String changeHeadImage(){
        return userMapper.changeHeadImage();
    }

}