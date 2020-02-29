package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CarStation;
import com.dccj.dao.CarStationMapper;
import com.dccj.service.CarStationService;

import java.util.List;

@Service
public class CarStationServiceImpl implements CarStationService{

    @Resource
    private CarStationMapper carStationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carStationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarStation record) {
        return carStationMapper.insert(record);
    }

    @Override
    public int insertSelective(CarStation record) {
        return carStationMapper.insertSelective(record);
    }

    @Override
    public CarStation selectByPrimaryKey(Integer id) {
        return carStationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarStation record) {
        return carStationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarStation record) {
        return carStationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CarStation> selectAllByNameLike(String likeName) {
        return carStationMapper.selectAllByNameLike(likeName);
    }
}
