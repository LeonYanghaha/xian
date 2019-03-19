package com.xian.demo.dao;

import com.xian.demo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Select(value = "SELECT * FROM PRODUCT LIMIT #{limit}")
    List<Product> findAll(@Param("limit") Integer limit);

    @Select(value = "SELECT pid, price, name, `desc`, stock, `type`, isRecommend, status, pushTime, producerId, sellNumber, imgUrl FROM PRODUCT WHERE pid=#{pid} LIMIT 1")
    Product findProductById(@Param("id") Integer pid);

    @Select(value = "SELECT * FROM PRODUCT LIMIT #{limit}")
    Product findProductByType(@Param("type") String type);

    @Select(value = "INSERT INTO PRODUCT (pid, price, name, `desc`, stock, `type`, " +
            "isRecommend, status, pushTime, producerId, sellNumber, imgUrl) " +
            "VALUES(#{pid}, #{Price}, #{Name}, #{desc}, #{Stock}, #{Type}, #{Recommend}, " +
            "#{Status}, #{PushTime}, #{ProducerId}, #{SellNumber}, #{ImgUrl})")
    Integer saveProduct(
            @Param("id") Integer pid,
            @Param("desc") String desc,
            @Param("name") String  Name,
            @Param("imgUrl") String ImgUrl,
            @Param("price") Double  Price,
            @Param("producerId") Short  ProducerId,
            @Param("pushTime") Date PushTime,
            @Param("type") Integer  Type,
            @Param("recommend") Boolean  Recommend,
            @Param("stock") Integer  Stock,
            @Param("sellNumber") Integer  SellNumber,
            @Param("status") Short  Status

    );
}
