package com.dccj.service;

import com.dccj.entity.CarOrder;

public interface CarOrderService {


    int deleteByPrimaryKey(Integer id);

    int insert(CarOrder record);

    int insertSelective(CarOrder record);

    CarOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrder record);

    int updateByPrimaryKey(CarOrder record);

}

