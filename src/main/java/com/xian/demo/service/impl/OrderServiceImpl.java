package com.xian.demo.service.impl;
import com.xian.demo.dao.AddressMapper;
import com.xian.demo.dao.OrderMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.*;
import com.xian.demo.service.OrderService;
import com.xian.demo.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @describe order的service层
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Integer payOrder(Integer oid, Integer uid) {
        return orderMapper.payOrder(oid, uid);
    }

    public V_user_order_detial getOrderById(Integer oid, Integer uid) {
        return orderMapper.getOrderById(oid, uid);
    }

    public List<Order> getOrderList(Integer uid) {

        List<Order> orderList = orderMapper.getOrderList(uid);
        if(orderList.size()<=0){
            return null;
        }else{
            return orderList;
        }

    }

    public Integer cancelOrder(Integer oid, Integer uid) {
        // 取消订单的时候，应该先检查该订单是否付款。只有未付款的订单才能取消
        return orderMapper.cancelOrder(oid, uid);
    }
//    aid, name, meta, pid, number, totalPrice, user.getId()
    public Integer submitOrder(Integer aid, String name, String  meta, Integer pid,
                               Integer number, Double totalPrice, Integer uid) {
//        1.检查库存，如果库存不够，返回, 检查传入的aid是否合法
//        2.修改库存和sellNumber
//        3.往order表中写入该订单, 并写入订单详情表
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

        Integer oid = Common.getOrderId();
        // 往订单表中写入数据
        Integer tempResultToOrder = orderMapper.submitOrder(uid, oid, aid, totalPrice, meta, name);
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
        return oid;
    }

    public Integer recivedOrder(Integer oid, Integer uid) {
        return orderMapper.recivedOrder(oid, uid);
    }
}
