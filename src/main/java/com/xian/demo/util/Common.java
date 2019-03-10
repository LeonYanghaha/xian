package com.xian.demo.util;
import java.util.Random;

public class Common {

    public static Integer getUserId(){
        Integer MAX = 999999999;
        Integer MIN = 100000000;
        return Common.getRandom(MAX,MIN);
    }

    public static Integer getProductId(){
        Integer MAX = 99999999;
        Integer MIN = 10000000;
        return Common.getRandom(MAX,MIN);
    }
    public static Integer getRandom(Integer MAX, Integer MIN){
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }


}
