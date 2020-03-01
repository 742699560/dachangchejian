package com.dccj.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.dccj.entity.DataDir;

public interface DataDirMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDir record);

    int insertSelective(DataDir record);

    DataDir selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDir record);

    int updateByPrimaryKey(DataDir record);

    List<DataDir> selectByType(@Param("type")String type);

}