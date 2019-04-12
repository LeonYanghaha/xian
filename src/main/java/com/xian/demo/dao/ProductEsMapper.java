package com.xian.demo.dao;

import com.xian.demo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductEsMapper extends ElasticsearchRepository<Product,String> {

    Product queryProductByPid (String pid);
}


