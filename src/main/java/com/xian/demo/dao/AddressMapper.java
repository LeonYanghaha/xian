package com.xian.demo.dao;

import com.xian.demo.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @describe 地址的持久层
 * @author yangk
 */
@Mapper
public interface AddressMapper {

    /**
     * @describe 获取用户所有的地址列表
     */
    @Select("")
    List<Address> getAddressList(Integer uid);

    /**
     * @describe 保存一个地址
     */
    @Insert("")
    Integer saveAddress(Address address);

    /**
     * @describe 删除一个地址
     */
    @Delete("")
    Integer removeAddress(Integer aid);

}
