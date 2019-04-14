package com.xian.demo.service;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Page;
import java.util.List;
import java.util.Map;

public interface ProductService {

//    设置商品的库存，并增加销量
    Integer setProductStockAndSellNumber(Integer pid, Integer number);

//    获取商品的库存
    List<Product> getProductStock(List<Integer> pidList);

    Page searchProductByKeyWord(String keyWord, Integer pageShowNumber, Integer currentPage);

    Page findAll(Integer pageShowNumber, Integer currentPage);

    Product findProductById(Integer id);

    Page findProductByType(Integer type , Integer pageShowNumber, Integer currentPage);

    List<Map<String, String>> getHotProduct(Integer size);

}
