package com.xian.demo.router;

import com.xian.demo.entity.Address;
import com.xian.demo.entity.Page;
import com.xian.demo.entity.Result;
import com.xian.demo.entity.User;
import com.xian.demo.service.AddressService;
import com.xian.demo.util.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "address/")
public class AddressRouter {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "getAddressList", method = RequestMethod.POST)
    public Result getAddressList(HttpServletRequest httpServletRequest,
                                 @RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber,
                                 @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){

        pageShowNumber = Common.checkParam(pageShowNumber, 10);
        currentPage = Common.checkParam(currentPage, 1);
        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());

        Page page = addressService.getAddressList(user.getId(), pageShowNumber, currentPage);
        if(null == page){
            return Result.ok("none");
        }else{
            return Result.ok(page);
        }
    }

    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public Result saveAddress(@Validated Address address, HttpServletRequest httpServletRequest){

        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
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

        User user = (User) httpServletRequest.getAttribute(Common.getReqUserKey());
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
