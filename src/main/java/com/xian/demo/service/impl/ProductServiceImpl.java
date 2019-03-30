package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Page;
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

    public List<Product> getProductStock(List<Integer> pidList) {
        return productMapper.getProductStock(pidList);
    }

    public  Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }

    public  Page findProductByType(Integer type , Integer pageShowNumber, Integer currentPage){

        Integer count = productMapper.countProduct();
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

        Integer count = productMapper.countProduct();
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
