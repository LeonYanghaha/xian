package com.xian.demo.dao;

import com.xian.demo.entity.Create;
import com.xian.demo.entity.Product;
import com.xian.demo.entity.ProductImg;
import com.xian.demo.entity.ProductType;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
@CacheConfig(cacheNames = "product")
public interface ProductMapper {


    /**
     * @describe  设置商品的库存和销量
     * @param {String}
     * @return {String}
     */
    @Update(value = "")
    Integer setProductStockAndSellNumber(@Param("pid") Integer pid,
                                         @Param("number") Integer number);

    /**
     * @describe 获取商品的库存
     * @param {Integer} pid
     * @return {Product}
     */
    @Select(value = "SELECT pid, stock FROM PRODUCT WHERE pid=#{pid} LIMIT 1")
    Product getProductStock(@Param("pid") Integer pid);



    /**
     * @describe 保存商品信息
     * @param {String}
     * @return {String}
     */
    @Insert(value = "INSERT INTO PRODUCT (price, name, `desc`, stock, PTYPE, isRecommend, status, pushTime, CID, sellNumber, imgUrl) " +
            "VALUES(#{price}, #{name}, #{desc}, #{stock}, #{PTYPE}, #{isRecommend}, #{status}, NOW(), #{CID}, #{sellNumber})")
    Integer saveProduct(@Param("price") Double price,
                        @Param("name") String name,
                        @Param("desc") String desc,
                        @Param("stock") Integer stock,
                        @Param("PTYPE") Integer PTYPE,
                        @Param("isRecommend") Boolean isRecommend,
                        @Param("status") Short status,
                        @Param("CID") Integer CID,
                        @Param("sellNumber") Integer sellNumber);
    /**
     * @describe 获取商品列表
     * @param {Integer} startNo
     * @param {Integer} pageSize
     * @return {List<Product>}
     */

    @Select(value = "SELECT pid, price, name, `desc`, stock, `ptype`, isRecommend, status, pushTime, cid, sellNumber, imgUrl " +
            "FROM PRODUCT LIMIT #{startNo},#{pageSize}")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "pid",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList")
            )
    })
    List<Product> findAll(@Param("startNo") Integer startNo,
                          @Param("pageSize") Integer pageSize);

    /**
     * @describe  通过ID查询商品
     * @param {Integer} id
     * @return {Product} Product
     */
    @Select(value = "SELECT pid, price, name, `desc`, stock, `ptype`, isRecommend, status, pushTime, cid, sellNumber, imgUrl FROM PRODUCT " +
                    "WHERE pid=#{id} LIMIT 1")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "pid",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList"))
    })
    @Cacheable(key ="#p0")
    Product findProductById(@Param("id") Integer id);


    /**
     * @describe 通过类型查询商品
     * @param {Integer} type
     * @param {Integer} startNo
     * @param {Integer} pageSize
     * @return {List<Product>} List<Product>
     */
    @Select(value = "SELECT pid, price, name, `desc`, stock, PTYPE, isRecommend, status, pushTime, CID, sellNumber, imgUrl FROM PRODUCT " +
                    "WHERE PTYPE = #{type} LIMIT #{startNo},#{pageSize}")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "pid",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList"))
    })
    List<Product> findProductByType(@Param("type") Integer type,
                                    @Param("startNo") Integer startNo,
                                    @Param("pageSize") Integer pageSize);

    // 查询productType
    @Select("SELECT PTYPE, PTYPENAME FROM PRODUCTTYPE WHERE PTYPE=#{ptype}")
    ProductType findProductTypeById(@Param("ptype") Integer ptype);

    // 查询生产厂家 Create
    @Select("SELECT CID, CNAME, CADDRESS, CDESC FROM `CREATE` WHERE CID=#{cid}")
    Create findCreateById(@Param("cid") Integer cid);

    // 查询  商品 图
    @Select("SELECT pid, name FROM xian.PRODUCTMG WHERE pid=#{pid}")
    ProductImg findImgList(@Param("pid") Integer pid);

}
