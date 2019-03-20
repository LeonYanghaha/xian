package com.xian.demo.router;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Result;
import com.xian.demo.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "product/")
public class ProductRouter {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "saveProduct",method = RequestMethod.GET)
    public Result saveProduct(){
        return Result.ok(productService.saveProduct(null));
    }
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Result findAll(@Param(value = "startNo") Integer startNo,
                          @Param(value = "pageSize") Integer pageSize){
        return Result.ok(productService.findAll(1,10));
    }

    @RequestMapping(value = "findByType/{type}",method = RequestMethod.GET)
    public Result findProductByType(@PathVariable(value = "type") Integer type){

        List<Product> product = productService.findProductByType(type,1,2);
        if(product.size()<=0){
            return Result.errorMsg("type 错误");
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
