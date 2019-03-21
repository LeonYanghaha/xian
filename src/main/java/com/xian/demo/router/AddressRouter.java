package com.xian.demo.router;

import com.xian.demo.entity.Address;
import com.xian.demo.entity.Result;
import com.xian.demo.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "address/")
public class AddressRouter {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "getAddressList", method = RequestMethod.POST)
    public Result getAddressList(@Param("uid") Integer uid){
        return Result.ok(addressService.getAddressList(uid));
    }

    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public Result saveAddress(@Validated Address address){
        return Result.ok(addressService.saveAddress(address));
    }

    @RequestMapping(value = "removeAddress", method = RequestMethod.POST)
    public Result removeAddress(@Param("uid") Integer aid){
        return Result.ok(addressService.removeAddress(aid));
    }

}
