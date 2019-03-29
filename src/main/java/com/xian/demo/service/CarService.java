package com.xian.demo.service;

import com.xian.demo.entity.Car;
import com.xian.demo.entity.CarDetial;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface CarService {

    Integer addItemToCar(@NotNull(message = "用户ID不能为空") Integer uid,
                         @NotNull(message = "商品ID不能为空") Integer pid);

    List<CarDetial> getCarList(Integer uid);

    Integer removeItem(@NotNull(message = "用户ID不能为空") Integer uid,
                       @NotNull(message = "商品ID不能为空") Integer pid);

}
