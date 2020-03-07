package com.dccj.dao;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.CarPrice;

public interface CarPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarPrice record);

    int insertSelective(CarPrice record);

    CarPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarPrice record);

    int updateByPrimaryKey(CarPrice record);

    CarPrice selectByTypeIdAndHeightFromAndHeightEnd(@Param("carType")String carType,@Param("heightFrom")BigDecimal heightFrom,@Param("heightEnd")BigDecimal heightEnd);

}