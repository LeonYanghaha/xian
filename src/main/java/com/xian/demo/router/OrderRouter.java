package com.xian.demo.router;

import com.xian.demo.entity.*;
import com.xian.demo.service.OrderService;
import com.xian.demo.util.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("order/")
public class OrderRouter {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "payOrder", method = RequestMethod.POST)
    public Result payOrder(HttpServletRequest httpServletRequest,
                           @Param("oid") Integer oid){

        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
        if(orderService.payOrder(oid, user.getId()) == 1){
            return Result.ok("支付成功");
        }else{
            return Result.errorMsg("未知错误");
        }
    }


    /**
     * @describe  提交订单
     * @param {String}
     * @return {String}
     */
    @CacheEvict(value = "findProductById", key = "'-'+ #pid") // 有用户提交订单后，需要修改库存，所以这里清空缓存
    @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
    public Result submitOrder(HttpServletRequest httpServletRequest,
                              @Param("aid") Integer aid,
                              @Param("name") String name,
                              @Param("order_meta") String order_meta,
                              @Param("pid") Integer pid,
                              @Param("number") Integer number,
                              @Param("total_price") Double total_price){
        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());

        Integer tempResult = orderService.submitOrder(aid, name, order_meta, pid, number, total_price, user.getId());
        if(tempResult == -1){
            return Result.errorMsg("库存不足");
        }else if(tempResult == 0){
            return Result.errorMsg("表更新失败");
        }else{
            return Result.ok(tempResult); // 将oid 返回给页面
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

        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
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
    public Result getOrderList(HttpServletRequest httpServletRequest,
                               @RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                               @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){

        pageShowNumber = Common.checkParam(pageShowNumber, 10);
        currentPage = Common.checkParam(currentPage, 1);

        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
        Page page = orderService.getOrderList(user.getId(), pageShowNumber, currentPage);
        if (null == page) {
            return Result.errorMsg("服务器异常");
        }else{
            return Result.ok(page);
        }
    }

    /**
     * @describe 取消订单
     * @param {String}
     * @return {String}
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public Result cancelOrder(HttpServletRequest httpServletRequest,
                              @Param("oid") Integer oid) {


        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
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
        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
        Integer tempResult = orderService.recivedOrder(oid , user.getId());
        if(tempResult == 1){
            return Result.ok("success");
        }else{
            return Result.errorMsg("操作失败");
        }
    }
    @RequestMapping(value = "removeOrder", method = RequestMethod.POST)
    public Result removeOrder(HttpServletRequest httpServletRequest,
                              @Param("oid") Integer oid){
        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
        Integer tempResult = orderService.recivedOrder(oid , user.getId());
        if(tempResult == 1){
            return Result.ok("success");
        }else{
            return Result.errorMsg("操作失败");
        }


    }
}