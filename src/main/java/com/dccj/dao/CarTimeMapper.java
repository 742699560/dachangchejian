package com.dccj.dao;

import com.dccj.entity.CarTime;

public interface CarTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarTime record);

    int insertSelective(CarTime record);

    CarTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarTime record);

    int updateByPrimaryKey(CarTime record);
}