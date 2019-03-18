package com.xian.demo.router;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Result;
import com.xian.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "product/")
public class ProductRouter {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Result findAll(){
        return Result.ok(productService.findAll());
    }

    @RequestMapping(value = "findByType/{type}",method = RequestMethod.GET)
    public Result findProductByType(@PathVariable(value = "type") String type){
        Product product = productService.findProductByType(type);
        if(product==null){
            return Result.errorMsg("id 错误");
        }else{
            return Result.ok(product);
        }
    }

    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
    public Result findProductById(@PathVariable(value = "id") Integer id){

        if(id<10000000 || id>99999999){
            return Result.errorMsg("参数错误");
        }
        Product product = productService.findProductById(id);
        if(product==null){
            return Result.errorMsg("id 错误");
        }else{
            return Result.ok(product);
        }
    }
}
