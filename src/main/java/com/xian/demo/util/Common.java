package com.xian.demo.util;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

@Component
public class Common {

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
    public static Integer getRandom(Integer MAX, Integer MIN){
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }

    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }



    public static String getReqUserKey() {
        return reqUserKey;
    }

    public static String getReqHeadKey() {
        return reqHeadKey;
    }
}
