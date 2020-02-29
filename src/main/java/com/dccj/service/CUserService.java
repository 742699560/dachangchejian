package com.dccj.service;

import com.dccj.entity.CUser;
public interface CUserService{


    int deleteByPrimaryKey(Integer id);

    int insert(CUser record);

    int insertSelective(CUser record);

    CUser selectByPrimaryKey(Integer id);

    CUser selectByName(String name);

    int updateByPrimaryKeySelective(CUser record);

    int updateByPrimaryKey(CUser record);

}
