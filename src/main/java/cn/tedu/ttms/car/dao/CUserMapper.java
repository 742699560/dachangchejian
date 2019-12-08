package cn.tedu.ttms.car.dao;

import cn.tedu.ttms.car.entity.CUser;

public interface CUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CUser record);

    int insertSelective(CUser record);

    CUser selectByPrimaryKey(Integer id);

    CUser selectByName(String name);

    int updateByPrimaryKeySelective(CUser record);

    int updateByPrimaryKey(CUser record);
}