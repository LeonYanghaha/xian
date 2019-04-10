package com.xian.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisTool {

    @Autowired
    private RedisTemplate redisTemplate;

    public void removeOrderFromQueue (Integer uid, Integer oid, Date timeStamp) {

        Map<String, Integer> orderDetial = new HashMap<>();
        orderDetial.put("oid", oid);
        orderDetial.put("uid", uid);
        //TODO 2019/4/10 11:01 AM  这里要减一的原因我也不知道，反正就是数据从数据库一进一出，导致有个差值1，应该是精度损失的原因吧
        orderDetial.put("time", Common.date2TimeStamp(timeStamp)-1 );
        System.out.println(orderDetial + "remove--->");
        redisTemplate.opsForList().remove(Common.getOrderQueueKey(),3,orderDetial);
    }

    public  void orderToQueue (Integer uid, Integer oid, Date timeStamp) {

        Map<String, Integer> orderDetial = new HashMap<>();
        orderDetial.put("oid", oid);
        orderDetial.put("uid", uid);
        orderDetial.put("time", Common.date2TimeStamp(timeStamp) );
        System.out.println(orderDetial + "SAVE--->");
        redisTemplate.opsForList().leftPush(Common.getOrderQueueKey(), orderDetial);
    }
}
