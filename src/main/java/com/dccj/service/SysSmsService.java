package com.dccj.service;

import com.dccj.entity.SysSms;
public interface SysSmsService{


    int deleteByPrimaryKey(Integer id);

    int insert(SysSms record);

    int insertSelective(SysSms record);

    SysSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSms record);

    int updateByPrimaryKey(SysSms record);

    SysSms selectByPhone(String phone);
}
