package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductEsMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.*;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
    private ProductEsMapper productEsMapper;

    @Override
    public com.xian.demo.entity.Page searchProductByKeyWord(String keyWord, Integer pageShowNumber, Integer currentPage) {

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", keyWord));
        queryBuilder.withPageable(PageRequest.of(currentPage, pageShowNumber));
        // 搜索，获取结果
        org.springframework.data.domain.Page<Product> products = productEsMapper.search(queryBuilder.build());
        // 总条数
        long total = products.getTotalElements();
        System.out.println("total = " + total);
        com.xian.demo.entity.Page tempPage = new com.xian.demo.entity.Page();

        tempPage.setCurrentPage(products.getNumber() + 1 );
        tempPage.setPageShowNumber(products.getSize());
        tempPage.setCount((int)products.getTotalElements());
        tempPage.setTotalPage(products.getTotalPages());

        List<Product> productList = new ArrayList<>();
        for (Product item : products) {
            productList.add(item);
        }
        tempPage.setData(productList);
        return  tempPage;
    }

    public List<Map<String, String>> getHotProduct (Integer size) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSort(SortBuilders.fieldSort("sellNumber").order(SortOrder.DESC));
        queryBuilder.withPageable(PageRequest.of(0, size));
        org.springframework.data.domain.Page<Product> items = productEsMapper.search(queryBuilder.build());
        List<Map<String, String>> productList = new ArrayList<>();
        for (Product item : items) {
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
