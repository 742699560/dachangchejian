package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CarTime;
import com.dccj.dao.CarTimeMapper;
import com.dccj.service.CarTimeService;

import java.util.List;

@Service
public class CarTimeServiceImpl implements CarTimeService {

    @Resource
    private CarTimeMapper carTimeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carTimeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarTime record) {
        return carTimeMapper.insert(record);
    }

    @Override
    public int insertSelective(CarTime record) {
        return carTimeMapper.insertSelective(record);
    }

    @Override
    public CarTime selectByPrimaryKey(Integer id) {
        return carTimeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarTime record) {
        return carTimeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarTime record) {
        return carTimeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CarTime> selectByStationIdOrderByTimeSub(Integer stationId,String date) {
        return carTimeMapper.selectByStationIdOrderByTimeSub(stationId,date);
    }
}

