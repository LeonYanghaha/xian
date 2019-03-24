package com.xian.demo.router;

import com.xian.demo.entity.*;
import com.xian.demo.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    public Result submitOrder(HttpServletRequest httpServletRequest,
                              @Param("meta") String meta,
                              @Param("totalPrice") Double totalPrice){
        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        Order order = new Order();
        order.setAddress(new Address(4,2, null,"","","",""));
        order.setMeta(meta);
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        order.setName("-------------");

        List<OrderDetial> orderDetialList = new ArrayList<>();
        orderDetialList.add(new OrderDetial(1,4,"aiya-1",499999,3.4,"meta1"));
        orderDetialList.add(new OrderDetial(1,6,"aiya-2",14,4.4,"meta2"));
        orderDetialList.add(new OrderDetial(1,5,"aiya-3",24,6.4,"meta3"));
        order.setOrderDetial(orderDetialList);

        Integer tempResult = orderService.submitOrder(order);
        if(tempResult == -1){
            return Result.errorMsg("库存不足");
        }else if(tempResult == 0){
            return Result.errorMsg("表更新失败");
        }else{
            return Result.ok("success");
        }
    }

    /**
     * @describe  通过订单ID获取订单详情
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "getOrderById", method = RequestMethod.POST)
    public Result getOrderById(HttpServletRequest httpServletRequest,
                               @Param("oid") Integer oid){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        V_user_order_detial v_user_order_detial = orderService.getOrderById(oid, user.getId());
        if(v_user_order_detial != null){
            return Result.ok(v_user_order_detial);
        }else{
            return Result.errorMsg("没有该订单");
        }
    }

    /**
     * @describe 获取订单列表
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public Result getOrderList(HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        return Result.ok(orderService.getOrderList(user.getId()));
    }

    /**
     * @describe 取消订单
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public Result cancelOrder(HttpServletRequest httpServletRequest,
                              @Param("oid") Integer oid) {


        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        Integer tempResult = orderService.cancelOrder(oid, user.getId());
        if (tempResult == 1) {
            return Result.ok("success");
        } else {
            return Result.errorMsg("操作失败");
        }
    }

    /**
     * @describe 确认收货
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "recivedOrder", method = RequestMethod.POST)
    public Result recivedOrder(HttpServletRequest httpServletRequest,
                               @Param("oid") Integer oid){
        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        Integer tempResult = orderService.recivedOrder(oid , user.getId());
        if(tempResult == 1){
            return Result.ok("success");
        }else{
            return Result.errorMsg("操作失败");
        }
    }


}
