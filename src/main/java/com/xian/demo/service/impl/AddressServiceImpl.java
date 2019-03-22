package com.xian.demo.service.impl;

import com.xian.demo.dao.AddressMapper;
import com.xian.demo.entity.Address;
import com.xian.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressList(@NotNull(message = "uid不能为空") Integer uid) {
        return addressMapper.getAddressList(uid);
    }

    @Override
    public Integer saveAddress(@Valid Address address) {

        return addressMapper.saveAddress(address.getUid(), address.getAname(), address.getAadderss(),
                                         address.getAtag(), address.getAphone());
    }

    @Override
    public Integer removeAddress(@NotNull(message = "aid不能为空")Integer aid,
                                 @NotNull(message = "uid不能为空")Integer uid) {
        return addressMapper.removeAddress(aid, uid);
    }
}
