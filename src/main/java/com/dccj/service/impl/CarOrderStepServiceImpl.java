package com.dccj.service.impl;

import com.dccj.entity.CarOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.dccj.dao.CarOrderStepMapper;
import com.dccj.entity.CarOrderStep;
import com.dccj.service.CarOrderStepService;

import java.util.List;

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

    @Override
    public CarOrderStep nextStep(CarOrder carOrder, Integer step, Integer sysUserId, Integer status) {
        CarOrderStep carOrderStep = this.selectByOrderIdAndStep(carOrder.getId(), step);
        carOrderStep.setSysUserId(sysUserId);
        carOrderStep.setStatus(status);
        carOrderStepMapper.updateByPrimaryKeySelective(carOrderStep);
        return carOrderStep;
    }

    @Override
    public CarOrderStep selectByOrderIdAndStep(Integer orderId, Integer step) {
        return carOrderStepMapper.selectByOrderIdAndStep(orderId, step);
    }

    @Override
    public List<CarOrderStep> selectByOrderId(Integer orderId) {
        return carOrderStepMapper.selectByOrderId(orderId);
    }
}

