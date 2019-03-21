package com.xian.demo.service.impl;

import com.xian.demo.dao.CarMapper;
import com.xian.demo.entity.Car;
import com.xian.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public Integer addItemToCar(Car car) {
        return null;
    }

    @Override
    public List<Car> getCarList(Integer uid) {
        return null;
    }

    @Override
    public Integer removeItem(Integer uid, Integer pid) {
        return null;
    }
}
