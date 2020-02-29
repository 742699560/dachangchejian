package com.dccj.service;

import com.dccj.entity.CarStation;

import java.util.List;

public interface CarStationService{


    int deleteByPrimaryKey(Integer id);

    int insert(CarStation record);

    int insertSelective(CarStation record);

    CarStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStation record);

    int updateByPrimaryKey(CarStation record);


    List<CarStation> selectAllByNameLike(String likeName);
}
