package com.dccj.dao;

import com.dccj.entity.CarOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarOrder record);

    int insertSelective(CarOrder record);

    CarOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrder record);

    int updateByPrimaryKey(CarOrder record);

    CarOrder selectByOrderNumber(@Param("orderNumber") String orderNumber);

    List<CarOrder> selectByUserIdOrderByCreateTimeDesc(@Param("userId") String userId);

    List<CarOrder> selectByCarId(@Param("carId") Integer carId);

    List<CarOrder> selectAllByStatusAndUsernameAndMobileAndCarNumAndStationId(@Param("dateBegin") String dateBegin,
                                                                              @Param("dateEnd") String dateEnd,
                                                                              @Param("status") Integer status,
                                                                              @Param("username") String username,
                                                                              @Param("mobile") String mobile,
                                                                              @Param("carNum") String carNum,
                                                                              @Param("stationId") Integer stationId,
                                                                              @Param("type") Integer type, @Param("sord") String sord, @Param("sidx") String sidx);

    List<Map> statisticsTopNum(@Param("date") String date);
}