package com.xian.demo.dao;

import com.xian.demo.entity.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductEsUtil {
    @Autowired
    private ProductEsMapper productEsMapper;

    public com.xian.demo.entity.Page searchProductByKeyWord (String keyWord, Integer pageShowNumber, Integer currentPage) {
        return getProductsByProp("", "", keyWord, "name", pageShowNumber, currentPage);
    }

    public com.xian.demo.entity.Page getHotProduct (Integer pageShowNumber, Integer currentPage) {
        return getProductsByProp("sellNumber", "desc", "", "", pageShowNumber, currentPage);
    }

    public com.xian.demo.entity.Page searchProductByPrice (Integer pageShowNumber, Integer currentPage) {
        return getProductsByProp("price", "asc", "", "", pageShowNumber, currentPage);
    }

//      orderProps  排序的字段
//      orderType   排序的类型
//      queryWord   查询的关键字
//      queryProps  查询的字段
//      pageShowNumber  页面展示的条数
//      currentPage   当面页数 ----这个页数指页面展示的页数，但是ES的页数从0 开始，需要每次过来减一
    private com.xian.demo.entity.Page getProductsByProp(String orderProps, String orderType,
                                                       String queryWord, String queryProps,
                                                       Integer pageShowNumber, Integer currentPage){

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        if (!queryProps.equals("") && !queryWord.equals("")) {
            queryBuilder.withQuery(QueryBuilders.matchQuery(queryProps, queryWord));
        }
        if (!orderProps.equals("") && !orderType.equals("")) {
            switch (orderType){
                case "asc":
                case "ASC":
                    queryBuilder.withSort(SortBuilders.fieldSort(orderProps).order(SortOrder.ASC));
                    break;
                default:
                    queryBuilder.withSort(SortBuilders.fieldSort(orderProps).order(SortOrder.DESC));
                    break;
            }
        }
        queryBuilder.withPageable(PageRequest.of(currentPage - 1, pageShowNumber));
        // 搜索，获取结果
        org.springframework.data.domain.Page<Product> products = productEsMapper.search(queryBuilder.build());
        com.xian.demo.entity.Page tempPage = new com.xian.demo.entity.Page();

        tempPage.setCurrentPage(products.getNumber() + 1);
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
}
