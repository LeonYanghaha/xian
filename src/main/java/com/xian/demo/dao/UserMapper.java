package com.xian.demo.dao;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import com.xian.demo.entity.User;

@Mapper
public interface UserMapper {

    @Select(value = " SELECT ID, un, phone, pw, headImage, status, registerTime, lastLoginTime FROM USER " +
                    "WHERE un=#{un} AND pw=#{pw} LIMIT 1")
    User login(@Param("un") String un ,@Param("pw") String pw);

    @Insert(value = "INSERT INTO USER (ID, un, phone, pw, headImage, status,registerTime) " +
                   "VALUES(#{id}, #{un}, #{phone}, #{pw}, #{headImage}, '0', #{registerTime})")
    Integer register(@Param("id") Integer id ,
                     @Param("un") String un ,
                     @Param("phone") String phone ,
                     @Param("pw") String pw ,
                     @Param("headImage") String headImage,
                     @Param("registerTime") Date registerTime);

    @Select(value = "SELECT * FROM PRODUCT LIMIT #{limit}")
    String changeHeadImage();

    @Select(value = "SELECT ID, un, phone, pw, headImage, status FROM USER WHERE un=#{un} LIMIT 1")
    User checkUn(@Param("un") String un);

    @Update(value = "UPDATE USER SET lastLoginTime=#{lastLoginTime} WHERE ID=#{id}")
    Integer updateLastLoginTime(@Param("lastLoginTime") Date lastLoginTime,
                                @Param("id") Integer id);
}
