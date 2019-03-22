package com.xian.demo.router;

import com.xian.demo.entity.Address;
import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "address/")
public class AddressRouter {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "getAddressList", method = RequestMethod.POST)
    public Result getAddressList(HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        return Result.ok(addressService.getAddressList(user.getId()));
    }

    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public Result saveAddress(@Validated Address address, HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        address.setUid(user.getId());
        Integer tempTag = addressService.saveAddress(address);

        if((tempTag).equals(1)){
            return Result.ok(null);
        }else{
            return Result.errorMsg("未知错误");
        }
    }

    @RequestMapping(value = "removeAddress", method = RequestMethod.POST)
    public Result removeAddress(@Param("aid") Integer aid, HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
        Integer tempFlag = addressService.removeAddress(aid, user.getId());

        if(tempFlag == 1){
            return Result.ok("删除成功");
        }else if(tempFlag ==0){
            return Result.errorMsg("删除失败");
        }else{
            return Result.errorMsg("未知错误");
        }

    }

}
