package com.dccj.service.impl;

import com.dccj.entity.CarOrderStep;
import com.dccj.service.CarOrderStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.dccj.entity.CarOrder;
import com.dccj.dao.CarOrderMapper;
import com.dccj.service.CarOrderService;

import java.util.Date;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    @Resource
    private CarOrderMapper carOrderMapper;
    @Resource
    private CarOrderStepService carOrderStepService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarOrder record) {
        return carOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(CarOrder record) {
        return carOrderMapper.insertSelective(record);
    }

    @Override
    public CarOrder selectByPrimaryKey(Integer id) {
        return carOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarOrder record) {
        return carOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarOrder record) {
        return carOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public CarOrder createOrder(CarOrder carOrder) {
        carOrder.setCreateTime(new Date());
        carOrder.setUpdateTime(new Date());
        this.insertSelective(carOrder);
        for (int i = 0; i < 10; i++) {
            CarOrderStep carOrderStep = new CarOrderStep();
            carOrderStep.setOrderId(carOrder.getId());
            carOrderStep.setName("");
            carOrderStep.setStatus(1);
            carOrderStep.setStep(i);
            carOrderStepService.insertSelective(carOrderStep);
        }
        return carOrder;
    }

}


