package com.dccj.dao;

import com.dccj.entity.DataDir;

public interface DataDirMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDir record);

    int insertSelective(DataDir record);

    DataDir selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDir record);

    int updateByPrimaryKey(DataDir record);
}