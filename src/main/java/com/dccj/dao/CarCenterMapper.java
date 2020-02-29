package com.dccj.dao;
import java.util.List;

import com.dccj.entity.CarCenter;
import org.apache.ibatis.annotations.Param;

public interface CarCenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarCenter record);

    int insertSelective(CarCenter record);

    CarCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCenter record);

    int updateByPrimaryKey(CarCenter record);

    List<CarCenter> selectAllByUserId(@Param("userId")Integer userId);

}