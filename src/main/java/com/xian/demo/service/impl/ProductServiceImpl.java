package com.xian.demo.service.impl;

import com.xian.demo.dao.ProductEsMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Page;
import com.xian.demo.entity.Product;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductEsMapper productEsMapper;

    public List<Product> getHotProduct (Integer size) {

        QueryStringQueryBuilder query = new QueryStringQueryBuilder("小米");
        Iterable<Product> searchResult = productEsMapper.search(query);
        Iterator<Product> iterator = searchResult.iterator();
        List<Product> productList = new ArrayList<>();
        while (iterator.hasNext()) {
            productList.add(iterator.next());
        }
        return productList;

//        searchSourceBuilder.timeout();
//        if (pageSize != 0) {
//            searchSourceBuilder.from(startPage);
//            searchSourceBuilder.size(pageSize);
//        }
//        searchSourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));
//
//        if (StringUtils.isNotEmpty(type)) {
//            searchRequest.types(type);
//        }
//
//         高亮（xxx=111,aaa=222）
//        if (StringUtils.isNotEmpty(highlightField)) {
//            HighlightBuilder highlightBuilder = new HighlightBuilder();
//            设置高亮
//            String preTags = "<span class='red'>";
//            String postTags = "</span>";
//            highlightBuilder.preTags(preTags);
//            highlightBuilder.postTags(postTags);
//             设置高亮字段
//            highlightBuilder.field(highlightField);
//            searchSourceBuilder.highlighter(highlightBuilder);
//        }
//        if (aggregationBuilder != null) {
//            searchSourceBuilder.aggregation(aggregationBuilder);
//        } else {
//            排序
//            order(searchSourceBuilder, orderBy, matchOrder);
//        }
//
//        打印的内容 可以在 Elasticsearch head 和 Kibana  上执行查询
//        logger.info("\n{}", searchSourceBuilder);
//        searchRequest.source(searchSourceBuilder);
//         执行搜索,返回搜索响应信息
//        SearchResponse searchResponse = client.search(searchRequest);
//
//        Aggregations aggregations = searchResponse.getAggregations();
//        聚合查询的内容
//        Terms fileTypeGroup = null;
//        if (aggregations != null && StringUtils.isNotBlank(aggName)) {
//            fileTypeGroup = aggregations.get(aggName);
//        }
//
//        long totalHits = searchResponse.getHits().getTotalHits();
//        long length = searchResponse.getHits().getHits().length;
//
//        logger.debug("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);
//
//        if (searchResponse.status().getStatus() == 200) {
//             解析对象
//            List<Map<String, Object>> sourceList = setSearchResponse(searchResponse,
//                    highlightField);
//            return new EsPage(startPage, pageSize, (int) totalHits, sourceList, fileTypeGroup);
//        }
//


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
    public  Page findProductByType(Integer type , Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--"+ Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));
        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        Page page = new Page();
        page.setAllProp(pageShowNumber, currentPage, count);
        List<Product> productList = productMapper.findProductByType(type , page.getStartIndex(), page.getEndIndex());
        if (productList.size() <= 0) {
            return null;
        }else{
            page.setData(productList);
            return page;
        }
    }
    public Page findAll(Integer pageShowNumber, Integer currentPage){

        logger.warn("cache miss--" + Thread.currentThread().getStackTrace()[1].getMethodName() + Common.getUserDate("yyyy-mm-dd  HH:mm:ss"));

        Integer count = productMapper.countProduct();
        if (count == 0) {
            return null;
        }
        Page page = new Page();
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
