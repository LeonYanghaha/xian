package com.xian.demo.dao;

import com.xian.demo.entity.OrderDetial;
import org.apache.ibatis.annotations.*;
import java.util.List;
import com.xian.demo.entity.Order;

/**
 * @describe order 的持久层
 * @author yangk
 */
@Mapper
public interface OrderMapper {

    @Select(value = "SELECT oid, uid, submitTime, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                         "meta, orderDetial, status, aname, aadderss, atag, aphone " +
                    "FROM xian.user_order_detial " +
                    "WHERE oid = #{oid} ")
    Order getOrderById(@Param("oid") Integer oid);

    @Select(value = "SELECT oid, uid, submitTime, payTime, pushTime, ReceivedTime, aid, totalPrice, " +
                            "meta, orderDetial, status, aname, aadderss, atag, aphone " +
                    "FROM xian.user_order_detial " +
                    "WHERE uid = #{uid} ")
    @Results({
            @Result(property = "orderDetial",
                    column = "oid",
                    many = @Many(select = "com.xian.demo.dao.OrderMapper.getOrderDetial")
            )
    })
    List<Order> getOrderList(@Param("uid") Integer uid);

    @Update(value = "")
    Integer cancelOrder(Order order);

    @Insert(value = "INSERT INTO xian.`ORDER` (uid, submitTime, aid, totalPrice, meta, status) " +
                    "VALUES(#{uid}, NOW(), #{aid}, #{totalPrice}, #{meta}, 10)")
    Integer submitOrder(@Param("uid") Integer uid,
                        @Param("aid") Integer aid,
                        @Param("totalPrice") Double totalPrice,
                        @Param("meta") String meta);


    //TODO 2019/3/23 10:21 AM  这里应该批量插入，而不是现在这种循环插入
    @Insert("INSERT INTO xian.ORDERDETIAL (oid, pid, `number`, price, meta) " +
            "VALUES(#{oid}, #{pid}, #{number}, #{price}, #{meta})")
    Integer insertOrderDetial(@Param("oid") Integer oid,
                              @Param("pid") Integer pid,
                              @Param("number") Integer number,
                              @Param("price") Double price,
                              @Param("meta") String meta);

    @Update(value = "")
    Integer recivedOrder(Order order);


    @Select("SELECT oid, pid, `number`, price, meta " +
            "FROM xian.ORDERDETIAL " +
            "WHERE oid = #{oid} ")
    List<OrderDetial> getOrderDetial(@Param("oid") Integer oid);

}
