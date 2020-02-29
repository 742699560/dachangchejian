package com.dccj.service;

import com.dccj.entity.CarCenter;
public interface CarCenterService{


    int deleteByPrimaryKey(Integer id);

    int insert(CarCenter record);

    int insertSelective(CarCenter record);

    CarCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCenter record);

    int updateByPrimaryKey(CarCenter record);

}
