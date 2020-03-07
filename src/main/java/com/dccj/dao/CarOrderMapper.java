package com.dccj.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.CarOrder;

public interface CarOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarOrder record);

    int insertSelective(CarOrder record);

    CarOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrder record);

    int updateByPrimaryKey(CarOrder record);

    CarOrder selectByOrderNumber(@Param("orderNumber")String orderNumber);

    List<CarOrder> selectByUserIdOrderByCreateTimeDesc(@Param("userId")String userId);

    List<CarOrder> selectByCarId(@Param("carId")Integer carId);

}