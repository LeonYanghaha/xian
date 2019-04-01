package com.xian.demo.service;

import com.xian.demo.entity.Page;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Validated
public interface CarService {

    Integer addItemToCar(@NotNull(message = "用户ID不能为空") Integer uid,
                         @NotNull(message = "商品ID不能为空") Integer pid);

    Page getCarList(Integer uid, Integer pageShowNumber, Integer currentPage);

    Integer removeItem(@NotNull(message = "用户ID不能为空") Integer uid,
                       @NotNull(message = "商品ID不能为空") Integer pid);

}
