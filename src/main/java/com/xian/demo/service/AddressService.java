package com.xian.demo.service;

import com.xian.demo.entity.Address;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface AddressService {

    List<Address> checkAid(@NotNull(message = "aid不能为空") Integer aid,
                     @NotNull(message = "uid不能为空") Integer uid);
    /**
     * @describe 获取用户所有的地址列表
     */
    List<Address> getAddressList(@NotNull(message = "uid不能为空")Integer uid);

    /**
     * @describe 保存一个地址
     */
    Integer saveAddress(@Valid Address address);

    /**
     * @describe 删除一个地址
     */
    Integer removeAddress(@NotNull(message = "aid不能为空") Integer aid,
                          @NotNull(message = "uid不能为空") Integer uid);

}
