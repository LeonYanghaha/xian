package com.xian.demo.service;

import com.xian.demo.entity.Address;
import java.util.List;

public interface AddressService {
    /**
     * @describe 获取用户所有的地址列表
     */
    List<Address> getAddressList(Integer uid);

    /**
     * @describe 保存一个地址
     */
    Integer saveAddress(Address address);

    /**
     * @describe 删除一个地址
     */
    Integer removeAddress(Integer aid);


}
