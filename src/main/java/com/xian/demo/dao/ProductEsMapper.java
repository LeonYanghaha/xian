package com.xian.demo.dao;

import com.xian.demo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEsMapper extends ElasticsearchRepository<Product,String> {

}


