package com.xian.demo.util;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Common {

    //TODO 2019/3/28 10:56 AM  @value的注解实在是搞不好了，看了好多的demo，还是null，算了，先就这样吧。后面再看。
    private static final String reqUserKey = "currentUser";
    private static final String reqHeadKey = "token";

    public static Integer checkParam(Integer val, Integer defauleVal){
        if(val<1){
            return defauleVal;
        }else{
            return val;
        }
    }

    public static Integer getOrderId(){
        Integer MAX = 999999999;
        Integer MIN = 100000000;
        return Common.getRandom(MAX,MIN);
    }
//
//    public static Integer getProductId(){
//        Integer MAX = 99999999;
//        Integer MIN = 10000000;
//        return Common.getRandom(MAX,MIN);
//    }
    public static Integer getRandom(Integer MAX, Integer MIN){
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }


    public static String getReqUserKey() {
        return reqUserKey;
    }

    public static String getReqHeadKey() {
        return reqHeadKey;
    }
}
