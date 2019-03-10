package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Product;
import com.xian.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }

    public List<Product> findAll(){
        return productMapper.findAll();
    }
}
