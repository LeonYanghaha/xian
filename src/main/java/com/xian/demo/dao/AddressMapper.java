package com.xian.demo.dao;

import com.xian.demo.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @describe 地址的持久层
 * @author yangk
 */
@Mapper
public interface AddressMapper {

    /**
     * @describe 获取用户所有的地址列表
     */
    @Select("SELECT aid, uid, `time`, aname, aadderss, atag, aphone  " +
            "FROM xian.ADDRESS " +
            "WHERE uid = #{uid}; ")
    List<Address> getAddressList(Integer uid);

    /**
     * @describe 保存一个地址
     */
    @Insert("INSERT INTO xian.ADDRESS (uid, `time`, aname, aadderss, atag, aphone ) " +
            "VALUES( #{uid}, NOW(), #{aname}, #{aadderss}, #{atag}, #{aphone}) ")
    Integer saveAddress(@Param("uid") Integer uid,
                        @Param("aname") String aname,
                        @Param("aadderss") String aadderss,
                        @Param("atag") String atag,
                        @Param("aphone") String aphone);


    /**
     * @describe 删除一个地址
     */
    @Delete("DELETE FROM xian.ADDRESS " +
            "WHERE aid = #{aid} AND uid = #{uid} ")
    Integer removeAddress(@Param("aid") Integer aid,
                          @Param("uid") Integer uid);

}
