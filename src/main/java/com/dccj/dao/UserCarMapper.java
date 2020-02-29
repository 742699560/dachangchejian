package com.dccj.dao;

import com.dccj.entity.UserCar;
import com.dccj.uitl.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCar record);

    int insertSelective(UserCar record);

    UserCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCar record);

    int updateByPrimaryKey(UserCar record);

    List<UserCar> selectByAll(UserCar userCar);

    Map<String, Object> selectSubTimes(Integer id);

    List<UserCar> selectByAllByPage(@Param("entity") UserCar userCar, @Param("pageObj") PageObject pageObject);
}