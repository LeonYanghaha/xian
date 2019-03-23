package com.xian.demo.dao;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.xian.demo.entity.Order;

/**
 * @describe order 的持久层
 * @author yangk
 */
@Mapper
public interface OrderMapper {

    @Select(value = "")
    Order getOrderById(Integer oid);

    @Select(value = "")
    List<Order> getOrderList(Integer uid);

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

}
