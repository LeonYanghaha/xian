package com.xian.demo.service;

import com.xian.demo.entity.Car;

import java.util.List;

public interface CarService {

    Integer addItemToCar(Car car);

    List<Car> getCarList(Integer uid);

    Integer removeItem(Integer uid, Integer pid);

}
