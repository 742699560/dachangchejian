package com.dccj.service;

import com.dccj.entity.DataDir;
public interface DataDirService{


    int deleteByPrimaryKey(Integer id);

    int insert(DataDir record);

    int insertSelective(DataDir record);

    DataDir selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDir record);

    int updateByPrimaryKey(DataDir record);

}
