package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.dao.CarOrderStepMapper;
import com.dccj.entity.CarOrderStep;
import com.dccj.service.CarOrderStepService;

@Service
public class CarOrderStepServiceImpl implements CarOrderStepService {

    @Resource
    private CarOrderStepMapper carOrderStepMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carOrderStepMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarOrderStep record) {
        return carOrderStepMapper.insert(record);
    }

    @Override
    public int insertSelective(CarOrderStep record) {
        return carOrderStepMapper.insertSelective(record);
    }

    @Override
    public CarOrderStep selectByPrimaryKey(Integer id) {
        return carOrderStepMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarOrderStep record) {
        return carOrderStepMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarOrderStep record) {
        return carOrderStepMapper.updateByPrimaryKey(record);
    }

}

