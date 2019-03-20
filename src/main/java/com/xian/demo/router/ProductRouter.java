package com.xian.demo.router;

import com.xian.demo.entity.Product;
import com.xian.demo.entity.Result;
import com.xian.demo.service.ProductService;
import com.xian.demo.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "product/")
public class ProductRouter {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "findAll/{limit}",method = RequestMethod.GET)
    public Result findAll(@PathVariable(value = "limit") Integer limit){
        return Result.ok(productService.findAll(limit));
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

    @RequestMapping("saveProduct")
    public Result saveProduct(){

        Product product = new Product();
        product.setDesc("desc");
        product.setImgUrl("wertyuioasdfghjk.jpg");
        product.setName("test");
        product.setPid(Common.getProductId());
        product.setPrice(34.56);
        product.setPushTime(new Date());
        product.setRecommend(true);
        product.setSellNumber(100);
        product.setStock(2333);
//        product.setType(45);
//        product.setProducerId((short)3);

        Integer tempTag = productService.saveProduct(product);
        return Result.ok(tempTag);
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
