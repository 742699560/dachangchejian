package com.dccj.service;

import com.dccj.entity.CarOrder;
import com.dccj.entity.CarOrderStep;

import java.util.List;

public interface CarOrderStepService {


    int deleteByPrimaryKey(Integer id);

    int insert(CarOrderStep record);

    int insertSelective(CarOrderStep record);

    CarOrderStep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrderStep record);

    int updateByPrimaryKey(CarOrderStep record);

    CarOrderStep nextStep(CarOrder carOrder, Integer step, Integer sysUserId, Integer status);

    CarOrderStep selectByOrderIdAndStep(Integer orderId, Integer step);

    List<CarOrderStep> selectByOrderId(Integer orderId);
}

