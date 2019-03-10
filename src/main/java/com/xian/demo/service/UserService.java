package com.xian.demo.service;

import com.xian.demo.entity.User;
import java.util.List;

public interface UserService {

    List<User> findAllUser();
    User login(String un, String pw);
    Integer register(User user);
    String changeHeadImage();
    Boolean checkUn(String un);
}