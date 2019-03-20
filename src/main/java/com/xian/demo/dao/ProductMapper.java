package com.xian.demo.dao;

import com.xian.demo.entity.Create;
import com.xian.demo.entity.Product;
import com.xian.demo.entity.ProductType;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {



    @Select(value = "SELECT * FROM PRODUCT LIMIT #{startNo},#{pageSize}")
    @Results({
            // 查询productType
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            // 查询生产厂家 Create
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateTypeById"))
    })
    List<Product> findAll(@Param("startNo") Integer startNo,
                          @Param("pageSize") Integer pageSize);


    @Select("SELECT PTYPE, PTYPENAME FROM PRODUCTTYPE WHERE PTYPE=#{ptype}")
    ProductType findProductTypeById(@Param("ptype") Integer ptype);

    @Select("SELECT CID, CNAME, CADDRESS, CDESC FROM `CREATE` WHERE CID=#{cid}")
    Create findCreateTypeById(@Param("cid") Integer cid);




    @Select(value = "SELECT pid, price, name, `desc`, stock, `type`, isRecommend, status, pushTime, producerId, sellNumber, imgUrl FROM PRODUCT WHERE pid=#{id} LIMIT 1")
    Product findProductById(@Param("id") Integer id);

    @Select(value = "SELECT * FROM PRODUCT LIMIT #{limit}")
    List<Product> findProductByType(@Param("type") String type);






//    @Select(value = "INSERT INTO PRODUCT (pid, price, name, `desc`, stock, `type`, " +
//            "isRecommend, status, pushTime, producerId, sellNumber, imgUrl) " +
//            "VALUES(#{pid}, #{Price}, #{Name}, #{desc}, #{Stock}, #{Type}, #{Recommend}, " +
//            "#{Status}, #{PushTime}, #{ProducerId}, #{SellNumber}, #{ImgUrl})")
//    Integer saveProduct(
//            @Param("id") Integer pid,
//            @Param("desc") String desc,
//            @Param("name") String  Name,
//            @Param("imgUrl") String ImgUrl,
//            @Param("price") Double  Price,
//            @Param("producerId") Short  ProducerId,
//            @Param("pushTime") Date PushTime,
//            @Param("type") Integer  Type,
//            @Param("recommend") Boolean  Recommend,
//            @Param("stock") Integer  Stock,
//            @Param("sellNumber") Integer  SellNumber,
//            @Param("status") Short  Status
//
//    );
}
