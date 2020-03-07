package com.dccj.service;

import com.dccj.entity.CarPrice;

import java.math.BigDecimal;

public interface CarPriceService {


    int deleteByPrimaryKey(Integer id);

    int insert(CarPrice record);

    int insertSelective(CarPrice record);

    CarPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarPrice record);

    int updateByPrimaryKey(CarPrice record);

    CarPrice selectByTypeIdAndHeightFromAndHeightEnd(String carType, BigDecimal heightFrom, BigDecimal heightEnd);
}

