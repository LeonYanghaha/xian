package com.xian.demo.router;

import com.xian.demo.entity.Car;
import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.CarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("car/")
public class CarRouter {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "addItemToCar", method = RequestMethod.POST)
    public Result addItemToCar(Car car, HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        car.setUid(user.getId());
        Integer tempFlag = carService.addItemToCar(car);
        if(tempFlag == 1){
            return Result.ok("success",null);
        }else{
            return Result.errorMsg("添加失败");
        }
    }
    
    @RequestMapping(value = "getCarList", method = RequestMethod.POST)
    public Result getCarList(HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        return Result.ok(carService.getCarList(user.getId()));
    }

    @RequestMapping(value = "removeItem", method = RequestMethod.POST)
    public Result removeItem(HttpServletRequest httpServletRequest, @Param("pid") Integer pid){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        Integer tempFlag = carService.removeItem(user.getId(), pid);
        if(tempFlag == 1 ){
            return Result.ok("删除成功");
        }else{
            return Result.errorMsg("删除失败");
        }

    }

}
