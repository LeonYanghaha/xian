package com.xian.demo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.xian.demo.dao.ProductEsMapper;
import com.xian.demo.entity.*;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "product/")
public class ProductRouter {

    @Autowired
    private ProductService productService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProductEsMapper productEsMapper;
    /**
     * @describe 获取特价商品，通过价格排序
     */
    @RequestMapping("price")
    public Result searchProductByPrice(@RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                                    @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){
        Page page  = productService.searchProductByPrice(pageShowNumber, currentPage);
        return Result.ok(page);
    }

    @RequestMapping("search/{kw}")
    public Result searchProductByKeyWord(@RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                                         @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                         @PathVariable(value = "kw") String keyWord) {

        Page page  = productService.searchProductByKeyWord(keyWord, pageShowNumber, currentPage);
        if (null == page) { // 没有查到对应的商品
            return Result.errorMsg("没有对应的商品");
        }else{
            return Result.ok(page);
        }
    }

    /**
     * @describe 搜索框下拉的提示列表，筛选销量最多的前十条数据
     */
    @RequestMapping("getHotProduct")
    public Result getHotProduct(@RequestParam(value = "size", defaultValue = "10") Integer size){
          return Result.ok(productService.getHotProduct(size));
    }

    @Cacheable(value="product_all", key = "'-' + #p0 + '-' + #p1")
    @RequestMapping(value = "findAll",method = RequestMethod.POST)
    public Result findAll(@RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                          @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {

        pageShowNumber = Common.checkParam(pageShowNumber, 10);
        currentPage = Common.checkParam(currentPage, 1);

        com.xian.demo.entity.Page page = productService.findAll(pageShowNumber, currentPage);
        if (null == page)  {
            return Result.errorMsg("暂无商品");
        }else{
            return Result.ok(page);
        }

    }

    @Cacheable(value="findByType", key = "'-' + #p1 + '-' + #p2")
    @RequestMapping(value = "findByType",method = RequestMethod.POST)
    public Result findProductByType(@Param(value = "type") Integer type,
                                    @RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                                    @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {

        pageShowNumber = Common.checkParam(pageShowNumber, 10);
        currentPage = Common.checkParam(currentPage, 1);

        com.xian.demo.entity.Page page  = productService.findProductByType(type, pageShowNumber, currentPage);
        if (null == page) { // 没有查到对应的商品
            return Result.errorMsg("没有对应的商品");
        }else{
            return Result.ok(page);
        }
    }
    //TODO 2019/4/8 10:47 AM  指定缓存的时效
    @Cacheable(value="findProductById", key = "'-' + #p0")
    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
    public Result findProductById(@PathVariable(value = "id") Integer id) {

        Product product = productService.findProductById(id);
        if (product == null) {
            return Result.ok("没对对应的商品ID",null);
        } else {
            return Result.ok(product);
        }
    }
}