package com.dccj.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.CarTime;

public interface CarTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarTime record);

    int insertSelective(CarTime record);

    CarTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarTime record);

    int updateByPrimaryKey(CarTime record);

    List<CarTime> selectByStationIdOrderByTimeSub(@Param("stationId")Integer stationId,@Param("date")String date);

    List<CarTime> selectAllByStationId(@Param("stationId")Integer stationId, @Param("sord") String sord, @Param("sidx") String sidx);
}