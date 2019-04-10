package com.xian.demo.service.impl;

import com.xian.demo.dao.AddressMapper;
import com.xian.demo.entity.Address;
import com.xian.demo.entity.Page;
import com.xian.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> checkAid(@NotNull(message = "aid不能为空") Integer aid, @NotNull(message = "uid不能为空") Integer uid) {
        return addressMapper.checkAid(aid, uid);
    }

    @Override
    public Page getAddressList(@NotNull(message = "uid不能为空") Integer uid,
                                        Integer pageShowNumber, Integer currentPage) {
        Integer count = addressMapper.getCount(uid);
        if (count == 0) {
            return null;
        }
        Page page = new Page();
        page.setAllProp(pageShowNumber, currentPage, count);

        List<Address>  addressList = addressMapper.getAddressList(uid, page.getStartIndex(), page.getEndIndex());
        if(addressList.size()<=0){
            return null;
        }else{
            page.setData(addressList);
            return page;
        }
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
