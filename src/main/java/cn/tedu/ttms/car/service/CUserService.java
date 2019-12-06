package cn.tedu.ttms.car.service;

import cn.tedu.ttms.car.entity.CUser;
public interface CUserService{


    int deleteByPrimaryKey(Integer id);

    int insert(CUser record);

    int insertSelective(CUser record);

    CUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CUser record);

    int updateByPrimaryKey(CUser record);

}
