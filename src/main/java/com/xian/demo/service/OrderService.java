package com.xian.demo.service;

import com.xian.demo.entity.Order;
import com.xian.demo.entity.V_user_order_detial;

import java.util.List;
public interface OrderService {

//    根据ID查询订单
    V_user_order_detial getOrderById(Integer oid, Integer uid);
//    查询用户的订单
    List<Order> getOrderList(Integer uid);
//    取消订单
    Integer cancelOrder(Integer oid, Integer uid);
//    提交订单
    Integer submitOrder(Order order);
//    确认收货
    Integer recivedOrder(Integer oid, Integer uid);

}
