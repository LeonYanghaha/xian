package com.xian.demo.service.impl;

import com.xian.demo.dao.OrderMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Order;
import com.xian.demo.entity.OrderDetial;
import com.xian.demo.entity.Product;
import com.xian.demo.service.OrderService;
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

    public Order getOrderById(Integer oid) {
        return null;
    }

    public List<Order> getOrderList(Integer uid) {

        return orderMapper.getOrderList(uid);

    }

    public Integer cancelOrder(Order order) {
        // 取消订单的时候，应该先检查该订单是否付款。只有未付款的订单才能取消
        return null;
    }

    public Integer submitOrder(Order order) {
//        1.检查库存，如果库存不够，返回
//        2.修改库存和sellNumber
//        3.往order表中写入该订单, 并写入订单详情表
        List<OrderDetial> orderDetialList  = order.getOrderDetial();
        List<Integer> pidList = new ArrayList<>();
        for (int i = 0; i < orderDetialList.size() ; i++) {
            OrderDetial orderDetial = orderDetialList.get(i);
            pidList.add(orderDetial.getPid());
        }

        List<Product> pidSockList = productMapper.getProductStock(pidList);
        Boolean isBack = false;

        goBack: for (int i = 0; i < orderDetialList.size() ; i++) {
            for (int j = 0; j < pidSockList.size(); j++) {
                if(pidSockList.get(j).getPid() != orderDetialList.get(i).getPid()){
                    continue;
                }
                if(pidSockList.get(j).getStock() < orderDetialList.get(i).getNumber()){
                    System.out.println("库存不够");
                    isBack = true;
                    break goBack;
                }
                System.out.print("库存充足");
                System.out.print(pidSockList.get(j));
                System.out.print(orderDetialList.get(i));
            }
        }

        if(isBack){
            // 存在库存不足的情况
            return -1;
        }

        // 往订单表中写入数据
        Integer tempResultToOrder = orderMapper.submitOrder(order.getUser().getId(), order.getAddress().getAid(),
                                                        order.getTotalPrice(), order.getMeta());
        if(tempResultToOrder != 1){
            return 0;  //数据库写入失败
        }

        Boolean insertIsOk = false;
        for (int i = 0; i < orderDetialList.size(); i++) {
            OrderDetial orderDetial = orderDetialList.get(i);
            Integer tempIsOk = orderMapper.insertOrderDetial(orderDetial.getOid(), orderDetial.getPid(),
                    orderDetial.getNumber(), orderDetial.getPrice(), orderDetial.getMeta());

            if(tempIsOk != 1){ // 插入失败的情况
                insertIsOk = true;
            }
        }

        if(insertIsOk){
            return 0;
        }

        Boolean isErrorFlag = false;
        // 修改库存和销量
        for (int i = 0; i < orderDetialList.size() ; i++) {
            Integer tempResult = productMapper.setProductStockAndSellNumber(orderDetialList.get(i).getPid(),
                                                                orderDetialList.get(i).getNumber());
            if(tempResult != 1 ){ // 更新失败的情况
                isErrorFlag = true;
                break;
            }
        }
        if(isErrorFlag){
            return 0;
        }
        return 1;
    }

    public Integer recivedOrder(Order order) {


        return null;
    }
}
