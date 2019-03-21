package com.xian.demo.service;

import com.xian.demo.entity.Product;

import java.util.List;

public interface ProductService {

//    设置商品的库存，并增加销量
    Integer setProductStockAndSellNumber(Integer pid, Integer number);
//    获取商品的库存
    Integer getProductStock(Integer pid);

    Integer saveProduct(Product product);

    List<Product> findAll(Integer startNo,Integer pageSize);

    Product findProductById(Integer id);

    List<Product> findProductByType(Integer type , Integer startNo,Integer pageSize);

}
