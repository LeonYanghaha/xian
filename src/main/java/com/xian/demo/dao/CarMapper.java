package com.xian.demo.dao;

import com.xian.demo.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @describe  购物车 持久层
 */
@Mapper
public interface CarMapper {

    Integer addItemToCar(Car car);

    List<Car> getCarList(Integer uid);

    Integer removeItem(Integer uid, Integer pid);

}
