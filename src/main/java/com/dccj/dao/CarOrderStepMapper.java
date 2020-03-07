package com.dccj.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.CarOrderStep;

public interface CarOrderStepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarOrderStep record);

    int insertSelective(CarOrderStep record);

    CarOrderStep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarOrderStep record);

    int updateByPrimaryKey(CarOrderStep record);


    CarOrderStep selectByOrderIdAndStep(@Param("orderId")Integer orderId,@Param("step")Integer step);

    List<CarOrderStep> selectByOrderId(@Param("orderId")Integer orderId);


}