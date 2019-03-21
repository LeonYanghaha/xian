package com.xian.demo.router;

import com.xian.demo.entity.Car;
import com.xian.demo.entity.Result;
import com.xian.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car/")
public class CarRouter {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "addItemToCar", method = RequestMethod.POST)
    public Result addItemToCar(Car car){
        return Result.ok(carService.addItemToCar(car));
    }
    
    @RequestMapping(value = "getCarList", method = RequestMethod.POST)
    public Result getCarList(Integer uid){
        return Result.ok(carService.getCarList(uid));
    }

    @RequestMapping(value = "removeItem", method = RequestMethod.POST)
    public Result removeItem(Integer uid, Integer pid){
          return Result.ok(carService.removeItem(uid, pid));
    }

}
