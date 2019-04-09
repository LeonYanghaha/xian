package com.xian.demo.router;

import com.xian.demo.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "page/")
public class PageRouter {

    /**
     * @describe
     */
    @RequestMapping(value = "getIndexSlide")
    public Result getIndexSlide(){

        Map<String, String> imgMap1 = new HashMap<>(),imgMap2= new HashMap<>(),
                            imgMap3= new HashMap<>(),imgMap4 = new HashMap<>();

        imgMap1.put("name", "name");
        imgMap1.put("url", "name");
        imgMap1.put("desc", "name");
        imgMap1.put("src", "name");

        imgMap2.put("name", "name");
        imgMap2.put("url", "name");
        imgMap2.put("desc", "name");
        imgMap2.put("src", "name");

        imgMap3.put("name", "name");
        imgMap3.put("url", "name");
        imgMap3.put("desc", "name");
        imgMap3.put("src", "name");

        imgMap4.put("name", "name");
        imgMap4.put("url", "name");
        imgMap4.put("desc", "name");
        imgMap4.put("src", "name");

        List<Map<String, String>> imgList = new ArrayList<>();
        imgList.add(imgMap1);
        imgList.add(imgMap2);
        imgList.add(imgMap3);
        imgList.add(imgMap4);

        return Result.ok(imgList);
    }

    @RequestMapping(value = "getMeanuList",method = RequestMethod.GET)
    public Result getMeanuList(){
        return Result.ok();
    }
}
