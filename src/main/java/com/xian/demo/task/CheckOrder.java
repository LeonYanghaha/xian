package com.xian.demo.task;

import com.xian.demo.dao.OrderMapper;
import com.xian.demo.service.impl.ProductServiceImpl;
import com.xian.demo.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@EnableScheduling   // 开启定时任务
@EnableAsync        // 开启多线程
public class CheckOrder {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Async
    @Scheduled(fixedDelay = 1000*30)  //间隔30秒
    public void chckOrder() {
        logger.info("job---check---order");
        Integer currentTime = Integer.valueOf(Common.timeStamp());
        ListOperations listOperations = redisTemplate.opsForList();
        Map<String, Integer> tempMap = (Map<String, Integer>) listOperations.rightPop(Common.getOrderQueueKey());
        if (null != tempMap) {
            // 拿到订单后，需要先判断是否超时。如果没超时，继续放入
            if((currentTime- tempMap.get("time")) <= 60*30) { // 超时时间， 单位是秒， 目前设置为三十分钟
                 // 时间未到的
                listOperations.rightPush(Common.getOrderQueueKey(),tempMap);
            }else{
                // 修改订单表中的状态
                Integer tempStatus = orderMapper.closeOrder( tempMap.get("oid"), tempMap.get("uid"));
                if (tempStatus.equals(1)) {
                    logger.info("订单超时未支付" + tempMap);
                } else {
                    logger.info("修改失败");
                    listOperations.rightPush(Common.getOrderQueueKey(),tempMap);
                }
            }
        }

    }
}