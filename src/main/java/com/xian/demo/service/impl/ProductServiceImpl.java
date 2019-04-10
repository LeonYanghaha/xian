package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Page;
import com.xian.demo.entity.Product;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public Integer setProductStockAndSellNumber(Integer pid, Integer number) {
        return productMapper.setProductStockAndSellNumber(pid, number);
    }
    public List<Product> getProductStock(List<Integer> pidList) {
        return productMapper.getProductStock(pidList);
    }
    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }
    public  Page findProductByType(Integer type , Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--"+ Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));
        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        Page page = new Page();
        page.setAllProp(pageShowNumber, currentPage, count);
        List<Product> productList = productMapper.findProductByType(type , page.getStartIndex(), page.getEndIndex());
        if(productList.size()<=0){
            return null;
        }else{
            page.setData(productList);
            return page;
        }
    }
    public Page findAll(Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--"+ Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));

        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        Page page = new Page();
        page.setAllProp(pageShowNumber, currentPage, count);
        List<Product> productList = productMapper.findAll(page.getStartIndex(), page.getEndIndex());
        if(productList.size()<=0){
            return null;
        }else{
            page.setData(productList);
            return page;
        }
    }
}
