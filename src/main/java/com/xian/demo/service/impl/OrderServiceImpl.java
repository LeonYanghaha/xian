package com.xian.demo.service.impl;

import com.xian.demo.dao.OrderMapper;
import com.xian.demo.entity.Order;
import com.xian.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @describe order的service层
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderById(Integer oid) {
        return null;
    }

    @Override
    public List<Order> getOrderList(Integer uid) {
        return null;
    }

    @Override
    public Integer cancelOrder() {
        return null;
    }

    @Override
    public Integer submitOrder() {
        return null;
    }

    @Override
    public Integer recivedOrder() {
        return null;
    }
}
