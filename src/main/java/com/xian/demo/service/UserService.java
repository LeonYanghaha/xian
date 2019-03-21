package com.xian.demo.service;

import com.xian.demo.entity.User;

public interface UserService {

//    List<User> findAllUser();
    User login(String un, String pw);
    Integer register(User user);
    String changeHeadImage();
    Boolean checkUn(String un);
}