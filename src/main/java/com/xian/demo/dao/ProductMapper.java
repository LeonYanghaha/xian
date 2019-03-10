package com.xian.demo.dao;

import com.xian.demo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findProductById(@Param("id") Integer pid);
}
