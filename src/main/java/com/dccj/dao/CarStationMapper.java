package com.dccj.dao;

import com.dccj.entity.CarStation;

public interface CarStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStation record);

    int insertSelective(CarStation record);

    CarStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStation record);

    int updateByPrimaryKey(CarStation record);
}