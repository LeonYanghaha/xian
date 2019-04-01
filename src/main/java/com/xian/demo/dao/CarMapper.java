package com.xian.demo.dao;

import com.xian.demo.entity.CarDetial;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @describe  购物车 持久层
 */
@Mapper
public interface CarMapper {


    /**
     * @describe 统计用户的购物车的商品总数
     */
    @Select("SELECT COUNT(0) " +
            "FROM xian.CAR " +
            "WHERE uid = #{uid} ")
    Integer carUserCount(@Param("uid") Integer uid);


    /**
     * @describe 添加商品到购物车
     */
    @Insert("INSERT INTO xian.CAR (uid, pid, addTime) " +
            "VALUES(#{uid}, #{pid}, NOW())")
    Integer addItemToCar(@Param("uid") Integer uid,
                         @Param("pid") Integer pid);

    /**
     * @describe 获取购物车商品列表
     */
    //TODO  2019-3-22 17:37 这里还有优化的空间，可以进行多表联查，就不需要这么麻烦了，这样对数据库做了多次的查询。后面抽空优化
    @Select("SELECT carid, uid, addTime, pid, pid AS PPID , price, name, `desc`, stock, " +
                "PTYPE, isRecommend, status, pushTime, CID, sellNumber, imgUrl " +
            "FROM xian.user_car_detial " +
            "WHERE uid = #{uid} " +
            "ORDER BY addTime DESC " +
            "LIMIT #{startIndex}, #{endIndex}")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "PPID",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList"))
    })
    List<CarDetial> getCarList(@Param("uid") Integer uid,
                               @Param("startIndex") Integer startIndex,
                               @Param("endIndex") Integer endIndex);



    /**
     * @describe 从购物车中移除
     */
    @Delete("DELETE FROM xian.CAR " +
            "WHERE uid = #{uid} AND pid = #{pid} ")
    Integer removeItem(@Param("uid") Integer uid,
                       @Param("pid") Integer pid);

    /**
     * @describe 检查商品是否已经在购物车里
     */
    @Select("SELECT COUNT(0) " +
            "FROM xian.CAR " +
            "WHERE uid=#{uid} AND pid=#{pid} ")
    Integer checkPidIsExist(@Param("uid") Integer uid,
                            @Param("pid") Integer pid);

}
