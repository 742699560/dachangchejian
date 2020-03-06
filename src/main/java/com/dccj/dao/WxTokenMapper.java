package com.dccj.dao;

import com.dccj.entity.WxToken;

public interface WxTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxToken record);

    int insertSelective(WxToken record);

    WxToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxToken record);

    int updateByPrimaryKey(WxToken record);
}