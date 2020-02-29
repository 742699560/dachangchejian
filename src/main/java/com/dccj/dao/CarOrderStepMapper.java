package com.dccj.dao;

import com.dccj.entity.CarOrderStep;

public interface CarOrderStepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarOrderStep record);

    int insertSelective(CarOrderStep record);

    CarOrderStep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrderStep record);

    int updateByPrimaryKey(CarOrderStep record);
}