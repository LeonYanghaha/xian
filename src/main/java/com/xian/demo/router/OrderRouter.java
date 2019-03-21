package com.xian.demo.router;

import com.xian.demo.entity.Result;
import com.xian.demo.entity.Order;
import com.xian.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("order/")
public class OrderRouter {

    @Autowired
    private OrderService orderService;

    /**
     * @describe  提交订单
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
    public Result submitOrder(@Validated Order order){
        return Result.ok(orderService.submitOrder(order));
    }

    /**
     * @describe  通过订单ID获取订单详情
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "getOrderById", method = RequestMethod.POST)
    public Result getOrderById(){
        return null;
    }

    /**
     * @describe 获取订单列表
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public Result getOrderList(){
        return null;
    }

    /**
     * @describe 取消订单
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public Result cancelOrder(){
        return null;
    }

    /**
     * @describe 确认收货
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "recivedOrder", method = RequestMethod.POST)
    public Result recivedOrder(){
        return null;
    }


}
