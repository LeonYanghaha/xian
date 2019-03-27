package com.xian.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xian")
@PropertySource({"classpath:application.yml"})
public class Common {

    @Value(value = "${xian.reqUserKey}")
    public static String reqUserKey;

    @Value(value = "${xian.reqHeadKey}")
    public static String reqHeadKey;

//    public static Integer getUserId(){
//        Integer MAX = 999999999;
//        Integer MIN = 100000000;
//        return Common.getRandom(MAX,MIN);
//    }
//
//    public static Integer getProductId(){
//        Integer MAX = 99999999;
//        Integer MIN = 10000000;
//        return Common.getRandom(MAX,MIN);
//    }
//    public static Integer getRandom(Integer MAX, Integer MIN){
//        Random rand = new Random();
//        return rand.nextInt(MAX - MIN + 1) + MIN;
//    }

}
