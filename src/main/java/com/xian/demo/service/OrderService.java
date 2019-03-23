package com.xian.demo.service;

import com.xian.demo.entity.Order;
import java.util.List;
public interface OrderService {

//    根据ID查询订单
    Order getOrderById(Integer oid, Integer uid);
//    查询用户的订单
    List<Order> getOrderList(Integer uid);
//    取消订单
    Integer cancelOrder(Order order);
//    提交订单
    Integer submitOrder(Order order);
//    确认收货
    Integer recivedOrder(Order order);

}
