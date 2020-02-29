package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CarPrice;
import com.dccj.dao.CarPriceMapper;
import com.dccj.service.CarPriceService;
@Service
public class CarPriceServiceImpl implements CarPriceService{

    @Resource
    private CarPriceMapper carPriceMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carPriceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarPrice record) {
        return carPriceMapper.insert(record);
    }

    @Override
    public int insertSelective(CarPrice record) {
        return carPriceMapper.insertSelective(record);
    }

    @Override
    public CarPrice selectByPrimaryKey(Integer id) {
        return carPriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarPrice record) {
        return carPriceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarPrice record) {
        return carPriceMapper.updateByPrimaryKey(record);
    }

}
