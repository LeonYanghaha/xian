package com.xian.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.xian.demo.entity.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update(value = "")
    Integer submitOrder(Order order);

    @Update(value = "")
    Integer recivedOrder(Order order);

}
