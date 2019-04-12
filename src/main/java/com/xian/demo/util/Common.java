package com.xian.demo.util;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

@Component
public class Common {

    private static final String reqUserKey = "currentUser";
    private static final String reqHeadKey = "token";
    private static final String orderQueueKey = "current_order";

    public static Integer checkParam(Integer val, Integer defauleVal){
        if(val<1){
            return defauleVal;
        }else{
            return val;
        }
    }

    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    public static Integer date2TimeStamp(Date date){

        Long temp =  date.getTime()/1000;
        return Integer.valueOf(temp+"");
    }

    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
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


    public static String getOrderQueueKey() {
        return orderQueueKey;
    }

    public static String getReqUserKey() {
        return reqUserKey;
    }

    public static String getReqHeadKey() {
        return reqHeadKey;
    }
}
