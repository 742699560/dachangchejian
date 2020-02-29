package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CarOrder;
import com.dccj.dao.CarOrderMapper;
import com.dccj.service.CarOrderService;
@Service
public class CarOrderServiceImpl implements CarOrderService{

    @Resource
    private CarOrderMapper carOrderMapper;

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

}
