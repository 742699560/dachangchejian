package com.dccj.service;

import com.dccj.entity.CarCenter;
import com.dccj.entity.CarOrder;
import com.dccj.entity.CarTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CarOrderService {


    int deleteByPrimaryKey(Integer id);

    int insert(CarOrder record);

    int insertSelective(CarOrder record);

    CarOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrder record);

    int updateByPrimaryKey(CarOrder record);

    CarOrder createOrder(CarOrder carOrder, CarCenter carCenter, CarTime carTime);

    CarOrder selectByOrderNumber(String orderNumber);

    CarOrder pay(CarOrder carOrder);

    List<CarOrder> selectByUserIdOrderByCreateTimeDesc(String userId);

    List<CarOrder> selectByCarId(Integer carId);

    List<CarOrder> selectAllByStatusAndUsernameAndMobileAndCarNumAndStationId(String dateBegin, String dateEnd, Integer status, String username, String mobile, String carNum, Integer stationId, Integer type,String sord, String sidx);

    List<Map> statisticsTopNum(String date);
}






