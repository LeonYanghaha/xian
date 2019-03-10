package com.xian.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import com.xian.demo.entity.User;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    List<User> findAll();
    User login(@Param("un") String un ,@Param("pw") String pw);
    Integer register(@Param("id") Integer id ,
                     @Param("un") String un ,
                     @Param("phone") String phone ,
                     @Param("pw") String pw ,
                     @Param("headImage") String headImage,
                     @Param("registerTime") Date registerTime);
    String changeHeadImage();
    User checkUn(@Param("un") String un);
    Integer updateLastLoginTime(@Param("lastLoginTime") Date lastLoginTime,
                                @Param("id") Integer id);
}
