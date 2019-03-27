package com.xian.demo.dao;

import com.xian.demo.entity.OrderDetial;
import com.xian.demo.entity.V_user_order_detial;
import org.apache.ibatis.annotations.*;
import java.util.List;
import com.xian.demo.entity.Order;

/**
 * @describe order 的持久层
 * @author yangk
 */
@Mapper
public interface OrderMapper {

    /**
     * @describe  通过订单ID获取用户的订单详情
     */
    @Select(value = "SELECT oid, oid AS ooid, uid, submitTime, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                         "meta, orderDetial, status, aname, aadderss, atag, aphone " +
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
    //TODO 2019/3/23 11:06 AM  订单列表页应该做分页查询
    @Select(value = "SELECT oid, oid AS ooid, uid, submitTime,name, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                            "meta, orderDetial, status, aname, aadderss, atag, aphone " +
                    "FROM xian.user_order_detial " +
                    "WHERE uid = #{uid} " +
                    "LIMIT 1, 10")
    @Results({
            @Result(property = "orderDetial",
                    column = "ooid",
                    many = @Many(select = "com.xian.demo.dao.OrderMapper.getOrderDetial")
            )
    })
    List<Order> getOrderList(@Param("uid") Integer uid);

    /**
     * @describe 用户主动取消订单。。。。仅限于未付款的订单
     */
    @Update(value = "UPDATE xian.`ORDER` " +
            "SET status = 80 " +
            "WHERE oid = #{oid} AND uid = #{uid} AND status = 20 ")
    Integer cancelOrder(@Param("oid")Integer oid,
                        @Param("uid")Integer uid);

    /**
     * @describe 提交订单
     */
    @Insert(value = "INSERT INTO xian.`ORDER` (uid, submitTime, aid, totalPrice, meta, status) " +
                    "VALUES(#{uid}, NOW(), #{aid}, #{totalPrice}, #{meta}, 10)")
    Integer submitOrder(@Param("uid") Integer uid,
                        @Param("aid") Integer aid,
                        @Param("totalPrice") Double totalPrice,
                        @Param("meta") String meta);


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
                    "SET status = 60 " +
                    "WHERE oid=#{oid} AND uid=#{uid}")
    Integer recivedOrder(@Param("oid")Integer oid,
                         @Param("uid")Integer uid);


    /**
     * @describe 获取订单详情表中的数据
     */
    @Select("SELECT oid, pid, `number`, price, meta " +
            "FROM xian.ORDERDETIAL " +
            "WHERE oid = #{oid} ")
    List<OrderDetial> getOrderDetial(@Param("oid") Integer oid);

}
