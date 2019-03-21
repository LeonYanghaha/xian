package com.xian.demo.service.impl;

import com.xian.demo.dao.AddressMapper;
import com.xian.demo.entity.Address;
import com.xian.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressList(Integer uid) {
        return addressMapper.getAddressList(uid);
    }

    @Override
    public Integer saveAddress(Address address) {
        return addressMapper.saveAddress(address);
    }

    @Override
    public Integer removeAddress(Integer aid) {
        return addressMapper.removeAddress(aid);
    }
}
