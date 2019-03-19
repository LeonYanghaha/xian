package com.xian.demo.service;

import com.xian.demo.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll(Integer limit);
    Product findProductById(Integer id);
    Product findProductByType(String type);
    Integer saveProduct(Product product);
}
