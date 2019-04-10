package com.xian.demo.service.impl;

import com.xian.demo.dao.AddressMapper;
import com.xian.demo.dao.OrderMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.*;
import com.xian.demo.service.OrderService;
import com.xian.demo.util.Common;
import com.xian.demo.util.RedisTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @describe order的service层
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Override
    public Integer payOrder(Integer oid, Integer uid) {

        // 先检查是否存在该订单
        V_user_order_detial order_detial = orderMapper.getOrderById(oid, uid);
        if(null== order_detial){
            return -1;
        }
        Integer tempStatus = orderMapper.payOrder(oid, uid);
        if (tempStatus == 1) {
            redisTool.removeOrderFromQueue(uid, oid, order_detial.getSubmitTime());
            return 1;
        } else {
            return -1;
        }
    }
    @Autowired
    private RedisTool redisTool;

    public V_user_order_detial getOrderById(Integer oid, Integer uid) {
        return orderMapper.getOrderById(oid, uid);
    }

    @Override
    public Integer removeOrder(Integer oid, Integer uid) {

        V_user_order_detial userOrder = orderMapper.getOrderById(oid, uid);

        if(null == userOrder){ // 没有改订单的情况
            return -1;
        }
        if (userOrder.getStatus()!=60 && userOrder.getStatus()!=80 && userOrder.getStatus()!=70) {
            return -2; // 当前订单不能删除
        }
        Integer integer = orderMapper.removeOrder(oid, uid);
        return integer;
    }

    public Page getOrderList(Integer uid, Integer pageShowNumber, Integer currentPage) {

        Integer count = orderMapper.getOrderCount(uid);
        Page page = new Page();
        page.setAllProp(pageShowNumber, currentPage, count);

        List<Order> orderList = orderMapper.getOrderList(uid, page.getStartIndex(), page.getEndIndex());
        if(orderList.size()<=0){
            return null;
        }else{
            page.setData(orderList);
            return page;
        }
    }

    public Integer cancelOrder(Integer oid, Integer uid) {
        // 取消订单的时候，应该先检查该订单是否付款。只有未付款的订单才能取消
         V_user_order_detial order = orderMapper.getOrderById(oid, uid);
         if(null == order){ // 没有改订单的情况
             return -1;
         }
         if (!order.getStatus().equals(10)) {
            return 0;
         }
        Integer tempFlag = orderMapper.cancelOrder(oid, uid);
        if(tempFlag.equals(1)){
            redisTool.removeOrderFromQueue(uid, oid, order.getSubmitTime());
            return 1;
        }else{
            return -1;
        }
    }
    public Integer submitOrder(Integer aid, String name, String  meta, Integer pid,
                               Integer number, Double totalPrice, Integer uid) {
//        1.检查库存，如果库存不够，返回, 检查传入的aid是否合法
//        2.修改库存和sellNumber
//        3.往order表中写入该订单, 并写入订单详情表  写入Redis
        List<Integer> pidList = new ArrayList<>();
        pidList.add(pid);
        List<Product> productList = productMapper.getProductStock(pidList);
        if(productList.size() != 1){// 传入的pid 没有对应的商品
            System.out.println("pid");
            return -1;
        }
        Product product = productList.get(0);
        if(product.getStock() < number){ // 库存不足的情况
            System.out.println("stock");
            return -1;
        }
        //检查传入的aid 是否合法
        List<Address> addressList = addressMapper.checkAid(aid, uid);
        if(addressList.size()<=0){// 传入的aid 不合法
            System.out.println(aid);
            return -1;
        }

        Double tempTotalPrice = product.getPrice() * number;
        if(!tempTotalPrice.equals(totalPrice)){ //总价计算不一致
            return -1;
        }

        Integer oid = Common.getOrderId();// 商品ID
        Date currentDate = new Date();
        // 往订单表中写入数据
        Integer tempResultToOrder = orderMapper.submitOrder(uid, oid, aid, totalPrice, meta, currentDate, name);
        if(tempResultToOrder != 1){
            System.out.println("order");
            return -1;  //数据库写入失败
        }
        // 往订单详情表插入数据
        Integer tempResult = orderMapper.insertOrderDetial(oid, pid, number, product.getPrice(), meta);
        if(tempResult != 1){
            System.out.println("orderdatial");
            return -1;
        }
        tempResult = productMapper.setProductStockAndSellNumber(pid, number);
        if(tempResult != 1){
            System.out.println("set stock");
            return -1;
        }

        // 代码执行到这里，就是往数据库里成功插入了订单信息。
        redisTool.orderToQueue(uid, oid, currentDate);
        return oid;
    }

    public Integer recivedOrder(Integer oid, Integer uid) {
        return orderMapper.recivedOrder(oid, uid);
    }
}
