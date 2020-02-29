package com.dccj.dao;

import com.dccj.entity.SysSms;

public interface SysSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSms record);

    int insertSelective(SysSms record);

    SysSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSms record);

    int updateByPrimaryKey(SysSms record);
}