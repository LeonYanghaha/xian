package com.xian.demo.dao;

import com.xian.demo.entity.Create;
import com.xian.demo.entity.Product;
import com.xian.demo.entity.ProductImg;
import com.xian.demo.entity.ProductType;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Date;
@Mapper
public interface ProductMapper {

    @Select(value = "SELECT * " +
                    " FROM PRODUCT " +
                    " WHERE pid = #{pid} " +
                    " LIMIT 1" )
    Product getProductById(@Param("pid") Integer pid);


    /**
     * 获取指定时间段内更新的商品ID
     */
    @Select("SELECT pid " +
            "FROM xian.PRODUCT " +
            "WHERE lastUpdateTime >= #{startTime} and lastUpdateTime <= #{endTime} " +
            "ORDER BY PRODUCT.lastUpdateTime DESC ")
    List<Integer> getProductIdByUpdateTime(@Param("startTime") Date startTime,
                                           @Param("endTime") Date endTime);

    /**
     * 获取商品表中最后修改的时间
     */
    @Select("SELECT lastUpdateTime " +
            "FROM xian.PRODUCT " +
            "ORDER BY lastUpdateTime DESC " +
            "LIMIT 0,1 ")
    Date getProductLastUpdateTime();

    /**
     * 校验pid 是否有效
     */
    @Select("SELECT COUNT(0) " +
            "FROM xian.PRODUCT " +
            "WHERE status = 0 AND pid = #{pid} ")
    Integer validatedPid(@Param("pid") Integer pid);


    /**
     *  设置商品的库存和销量
     */
    @Update(value = "UPDATE xian.PRODUCT " +
                    "SET stock = stock - #{number}, sellNumber = sellNumber + #{number} " +
                    "WHERE pid = #{pid} AND lastUpdateTime = NOW() ")
    Integer setProductStockAndSellNumber(@Param("pid") Integer pid,
                                         @Param("number") Integer number);

    /**
     *  获取商品的库存
     */
    @Select(value = "<script>" +
                    "SELECT pid, price, stock FROM PRODUCT " +
                    "WHERE pid In " +
                    "<foreach item='item' index='index' collection='pidList' open='(' separator=',' close=')' >" +
                    "   #{item} "+
                    "</foreach> " +
                    "</script>")
    List<Product> getProductStock(@Param("pidList") List<Integer> pidList);


    /**
     * 获取商品列表
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
     *  通过ID查询商品
     */
    @Select(value = " SELECT pid, pid AS PPID, price, name, `desc`, stock, `ptype`, isRecommend, status, pushTime, cid, sellNumber, imgUrl " +
                    " FROM PRODUCT " +
                    " WHERE pid = #{id} " +
                    " LIMIT 1" )
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
     *  通过类型查询商品
     */
    @Select(value = "SELECT pid, pid AS PPID, price, name, `desc`, stock, PTYPE, isRecommend, status, pushTime, CID, sellNumber, imgUrl " +
                    "FROM PRODUCT " +
                    "WHERE PTYPE = #{type} " +
                    "LIMIT #{startNo},#{pageSize}")
    //TODO 这里还有优化的空间，这里可以通过视图的方式去查询，而不是嵌套好多次的
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


    /**
     * 统计商品总数
     */
    @Select("select count(0) from XIAN.PRODUCT where status = 0")
    Integer countProduct();


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
