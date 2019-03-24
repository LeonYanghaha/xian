package com.xian.demo.router;

import com.xian.demo.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "page/")
public class PageRouter {
    @RequestMapping(value = "getMeanuList",method = RequestMethod.GET)
    public Result getMeanuList(){
        return Result.ok();
    }
}
