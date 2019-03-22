package com.xian.demo.dao;

import com.xian.demo.entity.Create;
import com.xian.demo.entity.Product;
import com.xian.demo.entity.ProductImg;
import com.xian.demo.entity.ProductType;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {


    /**
     * @describe  设置商品的库存和销量
     * @param {String}
     * @return {String}
     */
    @Update(value = "UPDATE xian.PRODUCT " +
                    "SET stock = stock - #{number}, sellNumber = sellNumber - #{number} " +
                    "WHERE pid = #{pid} ")
    Integer setProductStockAndSellNumber(@Param("pid") Integer pid,
                                         @Param("number") Integer number);

    /**
     * @describe 获取商品的库存
     * @param {Integer} pid
     * @return {Product}
     */
    @Select(value = "SELECT pid, stock FROM PRODUCT " +
                    "WHERE pid=#{pid} " +
                    "LIMIT 1")
    Product getProductStock(@Param("pid") Integer pid);


    /**
     * @describe 获取商品列表
     * @param {Integer} startNo
     * @param {Integer} pageSize
     * @return {List<Product>}
     */
    @Select(value = "SELECT pid, pid AS PPID, price, name, `desc`, stock, `ptype`, isRecommend, status, pushTime, cid, sellNumber, imgUrl " +
                    "FROM PRODUCT " +
                    "LIMIT #{startNo}, #{pageSize}")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "PPID",
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
    @Select(value = "SELECT pid, pid AS PPID, price, name, `desc`, stock, `ptype`, isRecommend, status, pushTime, cid, sellNumber, imgUrl" +
                    "FROM PRODUCT " +
                    "WHERE pid=#{id} " +
                    "LIMIT 1")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "PPID",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList"))
    })
    Product findProductById(@Param("id") Integer id);


    /**
     * @describe 通过类型查询商品
     * @param {Integer} type
     * @param {Integer} startNo
     * @param {Integer} pageSize
     * @return {List<Product>} List<Product>
     */
    @Select(value = "SELECT pid, pid AS PPID, price, name, `desc`, stock, PTYPE, isRecommend, status, pushTime, CID, sellNumber, imgUrl " +
                    "FROM PRODUCT " +
                    "WHERE PTYPE = #{type} " +
                    "LIMIT #{startNo},#{pageSize}")
    @Results({
            @Result(property = "productType",
                    column = "ptype",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findProductTypeById")),
            @Result(property = "create",
                    column = "cid",
                    one = @One (select = "com.xian.demo.dao.ProductMapper.findCreateById")),
            @Result(property = "productImgList",
                    column = "PPID",
                    many = @Many (select = "com.xian.demo.dao.ProductMapper.findImgList"))
    })
    List<Product> findProductByType(@Param("type") Integer type,
                                    @Param("startNo") Integer startNo,
                                    @Param("pageSize") Integer pageSize);

    // 查询productType
    @Select("SELECT PTYPE, PTYPENAME " +
            "FROM PRODUCTTYPE " +
            "WHERE PTYPE=#{ptype}")
    ProductType findProductTypeById(@Param("ptype") Integer ptype);

    // 查询生产厂家 Create
    @Select("SELECT CID, CNAME, CADDRESS, CDESC " +
            "FROM `CREATE` " +
            "WHERE CID=#{cid}")
    Create findCreateById(@Param("cid") Integer cid);

    // 查询  商品 图
    @Select("SELECT iid, pid, name " +
            "FROM xian.PRODUCTMG " +
            "WHERE pid=#{pid}")
    ProductImg findImgList(@Param("pid") Integer pid);

}
