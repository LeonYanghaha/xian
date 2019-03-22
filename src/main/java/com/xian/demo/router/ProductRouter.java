package com.xian.demo.router;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Result;
import com.xian.demo.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "product/")
public class ProductRouter {

    @Autowired
    private ProductService productService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

//    @Cacheable(value="product_all")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Result findAll(@Param(value = "startNo") Integer startNo,
                          @Param(value = "pageSize") Integer pageSize){

        List<Product> productList = productService.findAll(startNo, pageSize);
        if(productList.size()<=0){
            return Result.ok("暂无商品",null);
        }else{
            return Result.ok(productList);
        }

    }

//    @Cacheable(value="product_type")
    @RequestMapping(value = "findByType",method = RequestMethod.GET)
    public Result findProductByType(@Param(value = "type") Integer type,
                                    @Param(value = "startNo") Integer startNo,
                                    @Param(value = "pageSize") Integer pageSize){

        List<Product> product = productService.findProductByType(type, startNo, pageSize);
        if(product.size() <= 0 ){// 没有查到对应的商品
            return Result.ok("没有对应的商品", null);
        }else{
            return Result.ok(product);
        }
    }

    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
    public Result findProductById(@PathVariable(value = "id") Integer id){

        Product product = productService.findProductById(id);
        if(product==null){
            return Result.ok("没对对应的商品ID",null);
        }else{
            return Result.ok(product);
        }
    }
}
