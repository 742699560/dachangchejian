package com.dccj.service;

import com.dccj.entity.CarTime;

import java.util.List;

public interface CarTimeService {


    int deleteByPrimaryKey(Integer id);

    int insert(CarTime record);

    int insertSelective(CarTime record);

    CarTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarTime record);

    int updateByPrimaryKey(CarTime record);

    List<CarTime> selectByStationIdOrderByTimeSub(Integer stationId,String date);

    List<CarTime> selectAllByStationId(Integer stationId, String sord, String sidx);
}

