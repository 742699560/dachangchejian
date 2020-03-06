package com.dccj.service;

import com.dccj.entity.WxToken;

public interface WxTokenService {


    int deleteByPrimaryKey(Integer id);

    int insert(WxToken record);

    int insertSelective(WxToken record);

    WxToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxToken record);

    int updateByPrimaryKey(WxToken record);

}


