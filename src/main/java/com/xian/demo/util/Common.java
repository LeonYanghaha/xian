package com.xian.demo.util;
import com.xian.demo.entity.Product;

import java.util.Random;
import java.util.List;

public class Common {

    public static List<Product> formatImgUrl(List<Product> productList){

        System.out.println(productList);
        for(int i=0;i< productList.size();i++){
            List<String> urlList = productList.get(i).getImgUrl();
            if(urlList==null){
                continue;
            }
            for(int j =0; j<=urlList.size();j++){
                if(urlList.get(j)==null){
                   continue;
                }
                urlList.set(j,"http://127.0.0.1:8888/img/"+urlList.get(j));
                System.out.println(urlList.get(j));
            }
        }
        return productList;
    }

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
