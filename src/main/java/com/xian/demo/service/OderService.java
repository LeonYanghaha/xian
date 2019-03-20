package com.xian.demo.service;

import com.xian.demo.entity.Order;
import java.util.List;
public interface OderService {

//    根据ID查询订单
    Order getOrderById(Integer oid);
//    查询用户的订单
    List<Order> getOrderList(Integer uid);
//    取消订单
    Integer cancelOrder();
//    提交订单
    Integer submitOrder();
//    确认收货
    Integer recivedOrder();

}
