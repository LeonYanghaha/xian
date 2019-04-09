package com.xian.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RedisTool {

    @Autowired
    private RedisTemplate redisTemplate;

    public void removeOrderFromQueue (Integer uid, Integer oid) {
        Map<String, Integer> orderDetial = new HashMap<>();
        orderDetial.put("oid", oid);
        orderDetial.put("uid", uid);
        redisTemplate.opsForList().remove(Common.getOrderQueueKey(),3,orderDetial);
    }

    public  void orderToQueue (Integer uid, Integer oid, Integer timeStamp) {
        Map<String, Integer> orderDetial = new HashMap<>();
        orderDetial.put("oid", oid);
        orderDetial.put("uid", uid);
        orderDetial.put("time",timeStamp);
        redisTemplate.opsForList().leftPush(Common.getOrderQueueKey(),orderDetial );
    }

}
