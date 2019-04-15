package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductEsMapper;
import com.xian.demo.dao.ProductEsUtil;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.*;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductEsUtil productEsUtil;

    public com.xian.demo.entity.Page searchProductByPrice(Integer pageShowNumber, Integer currentPage){
       return productEsUtil.searchProductByPrice(pageShowNumber, currentPage);
    }

    @Override
    public com.xian.demo.entity.Page searchProductByKeyWord(String keyWord, Integer pageShowNumber, Integer currentPage) {
        return productEsUtil.searchProductByKeyWord(keyWord, pageShowNumber, currentPage);
    }

    public List<Map<String, String>> getHotProduct (Integer size) {
        com.xian.demo.entity.Page page = productEsUtil.getHotProduct(size, 1);
        if (page.getData() == null){
            return null;
        }
        List<Product> products = (List<Product>)page.getData();
        List<Map<String, String>> productList = new ArrayList<>();
        for (Product item : products) {
            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("value", item.getName());
            productList.add(stringMap);
        }
        return productList;
    }

    public Integer setProductStockAndSellNumber(Integer pid, Integer number) {
        return productMapper.setProductStockAndSellNumber(pid, number);
    }

    public List<Product> getProductStock(List<Integer> pidList) {
        return productMapper.getProductStock(pidList);
    }

    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }

    public  com.xian.demo.entity.Page findProductByType(Integer type , Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--"+ Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));
        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        com.xian.demo.entity.Page page = new com.xian.demo.entity.Page();
        page.setAllProp(pageShowNumber, currentPage, count);
        List<Product> productList = productMapper.findProductByType(type , page.getStartIndex(), page.getEndIndex());
        if (productList.size() <= 0) {
            return null;
        }else{
            page.setData(productList);
            return page;
        }
    }

    public com.xian.demo.entity.Page findAll(Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--" + Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));
        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        com.xian.demo.entity.Page page = new com.xian.demo.entity.Page();
        page.setAllProp(pageShowNumber, currentPage, count);
        List<Product> productList = productMapper.findAll(page.getStartIndex(), page.getEndIndex());
        if (productList.size() <= 0) {
            return null;
        } else {
            page.setData(productList);
            return page;
        }
    }
}
