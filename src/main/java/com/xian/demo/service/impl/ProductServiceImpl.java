package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Product;
import com.xian.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    public Integer saveProduct(Product product){
        logger.info("saveProduct", product);
        return productMapper.saveProduct(
                product.getPid(),
                product.getDesc(),
                product.getName(),
                product.getImgUrl(),
                product.getPrice(),
                product.getProducerId(),
                product.getPushTime(),
                product.getType(),
                product.getRecommend(),
                product.getStock(),
                product.getSellNumber(),
                product.getStatus()
        );
    }

    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }
    public  Product findProductByType(String type){
            return productMapper.findProductByType(type);
        }

    public List<Product> findAll(Integer limit){
        logger.info("saveProduct", limit);
        return productMapper.findAll(limit);
    }
}
