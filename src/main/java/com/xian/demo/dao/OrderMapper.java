package com.xian.demo.dao;

import com.xian.demo.entity.OrderDetial;
import com.xian.demo.entity.V_user_order_detial;
import org.apache.ibatis.annotations.*;
import java.util.Date;
import java.util.List;
import com.xian.demo.entity.Order;

/**
 * @describe order 的持久层
 * @author yangk
 */
@Mapper
public interface OrderMapper {

    @Delete("DELETE FROM xian.`ORDER` " +
            "WHERE oid=#{oid} AND uid=#{uid}")
    Integer removeOrder(@Param("oid") Integer oid,
                        @Param("uid") Integer uid);

    /**
     * @describe 给订单付款
     */
    @Update(value = "UPDATE xian.`ORDER` " +
            "SET status = 30 , payTime = NOW() " +
            "WHERE oid = #{oid} AND uid = #{uid}")
    Integer payOrder(@Param("oid") Integer oid,
                     @Param("uid") Integer uid);


    /**
     * @describe  通过订单ID获取用户的订单详情
     */
    @Select(value = "SELECT oid, oid AS ooid, uid, submitTime, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                         "meta, orderDetial, status, aname, name, aadderss, atag, aphone " +
                    "FROM xian.user_order_detial " +
                    "WHERE oid = #{oid} AND uid = #{uid} ")
    @Results({
            @Result(property = "orderDetial",
                    column = "ooid",
                    many = @Many(select = "com.xian.demo.dao.OrderMapper.getOrderDetial"))
    })
    V_user_order_detial getOrderById(@Param("oid") Integer oid,
                                     @Param("uid") Integer uid);


    /**
     * @describe 获取用户的订单列表
     */
    @Select(value = "SELECT oid, oid AS ooid, uid, submitTime,name, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                            "meta, orderDetial, status, aname, aadderss, atag, aphone " +
                    "FROM xian.user_order_detial " +
                    "WHERE uid = #{uid} " +
                    "ORDER BY submitTime DESC " +
                    "LIMIT #{startIndex}, #{endIndex}")
    @Results({
            @Result(property = "orderDetial",
                    column = "ooid",
                    many = @Many(select = "com.xian.demo.dao.OrderMapper.getOrderDetial")
            )
    })
    List<Order> getOrderList(@Param("uid") Integer uid,
                             @Param("startIndex") Integer startIndex,
                             @Param("endIndex") Integer endIndex);

    /**
     * @describe 用户主动取消订单。。。。仅限于未付款的订单
     */
    @Update(value = "UPDATE xian.`ORDER` " +
            "SET status = 80 " +
            "WHERE oid = #{oid} AND uid = #{uid} ")
    Integer cancelOrder(@Param("oid")Integer oid,
                        @Param("uid")Integer uid);

    /**
     * @describe 系统自动关闭订单，限于超时未支付的订单
     */
    @Update(value = "UPDATE xian.`ORDER` " +
            "SET status = 70 " +
            "WHERE oid = #{oid} AND uid = #{uid} ")
    Integer closeOrder(@Param("oid")Integer oid,
                       @Param("uid")Integer uid);

    /**
     * @describe 提交订单
     */
    @Insert(value = "INSERT INTO xian.`ORDER` (oid, uid, submitTime, aid, totalPrice, meta, status, name) " +
                    "VALUES(#{oid}, #{uid}, #{submitDate}, #{aid}, #{totalPrice}, #{meta}, 10, #{name})")
    Integer submitOrder(@Param("uid") Integer uid,
                        @Param("oid") Integer oid,
                        @Param("aid") Integer aid,
                        @Param("totalPrice") Double totalPrice,
                        @Param("meta") String meta,
                        @Param("submitDate") Date submitDate,
                        @Param("name") String name);


    /**
     * @describe 插入订单详情表
     */
    //TODO 2019/3/23 10:21 AM  这里应该批量插入，而不是现在这种循环插入
    @Insert("INSERT INTO xian.ORDERDETIAL (oid, pid, `number`, price, meta) " +
            "VALUES(#{oid}, #{pid}, #{number}, #{price}, #{meta})")
    Integer insertOrderDetial(@Param("oid") Integer oid,
                              @Param("pid") Integer pid,
                              @Param("number") Integer number,
                              @Param("price") Double price,
                              @Param("meta") String meta);

    /**
     * @describe 确认收货
     */
    @Update(value = "UPDATE xian.`ORDER` " +
                    "SET status = 60 , ReceivedTime = NOW() " +
                    "WHERE oid=#{oid} AND uid=#{uid}")
    Integer recivedOrder(@Param("oid")Integer oid,
                         @Param("uid")Integer uid);


    /**
     * @describe 获取订单详情表中的数据
     */
    @Select("SELECT * from(" +
                "SELECT oid, ORDERDETIAL.pid, `number`, ORDERDETIAL.price, meta, product.name AS pname " +
                "FROM xian.ORDERDETIAL LEFT JOIN PRODUCT on product.pid = ORDERDETIAL.pid) AS temp " +
            "where temp.oid= #{oid} ")
    List<OrderDetial> getOrderDetial(@Param("oid") Integer oid);


    @Select("select count(0) from XIAN.order where Uid = #{uid}")
    Integer getOrderCount(@Param("uid") Integer uid);

}
