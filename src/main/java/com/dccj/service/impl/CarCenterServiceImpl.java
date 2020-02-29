package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CarCenter;
import com.dccj.dao.CarCenterMapper;
import com.dccj.service.CarCenterService;
@Service
public class CarCenterServiceImpl implements CarCenterService{

    @Resource
    private CarCenterMapper carCenterMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carCenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarCenter record) {
        return carCenterMapper.insert(record);
    }

    @Override
    public int insertSelective(CarCenter record) {
        return carCenterMapper.insertSelective(record);
    }

    @Override
    public CarCenter selectByPrimaryKey(Integer id) {
        return carCenterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarCenter record) {
        return carCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarCenter record) {
        return carCenterMapper.updateByPrimaryKey(record);
    }

}
