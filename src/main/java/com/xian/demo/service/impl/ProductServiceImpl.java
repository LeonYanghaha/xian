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

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    public Integer setProductStockAndSellNumber(Integer pid, Integer number) {
        return productMapper.setProductStockAndSellNumber(pid, number);
    }

    public Integer getProductStock(Integer pid) {
        return productMapper.getProductStock(pid).getStock();
    }

//    public Integer saveProduct(Product product){
//        return productMapper.saveProduct(product.getPrice(), product.getName(), product.getDesc(),
//                                         product.getStock(), product.getProductType().getPtype(), product.getRecommend(),
//                                         product.getStatus(), product.getCreate().getCid(),
//                                         product.getSellNumber() );
//    }

    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }

    public  List<Product> findProductByType(Integer type , Integer startNo,Integer pageSize){
            return productMapper.findProductByType(type , startNo, pageSize);
    }

    public List<Product> findAll(Integer startNo,Integer pageSize){
        return productMapper.findAll(startNo,pageSize);
    }
}
