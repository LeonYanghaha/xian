package com.xian.demo.router;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Result;
import com.xian.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "product/")
public class ProductRouter {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "findall",method = RequestMethod.GET)
    public Result findAll(){
        return new Result((short)0,"success",productService.findAll());
    }
    @RequestMapping(value = "find/{id}",method = RequestMethod.GET)
    public Result findProductById(@PathVariable(value = "id") Integer id){

        if(id<10000000 || id>99999999){
            return new Result((short)-1,"参数错误",null);
        }
        Product product = productService.findProductById(id);
        if(product==null){
            return new Result((short)-1,"id 错误",null);
        }else{
            return new Result((short)0,"success",product);
        }


    }
}
