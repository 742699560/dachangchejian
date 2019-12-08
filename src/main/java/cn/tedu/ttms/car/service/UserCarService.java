package cn.tedu.ttms.car.service;

import cn.tedu.ttms.car.entity.UserCar;

import java.util.List;
import java.util.Map;

public interface UserCarService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCar record);

    int insertSelective(UserCar record);

    UserCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCar record);

    int updateByPrimaryKey(UserCar record);

    List<UserCar> selectByAll(UserCar record);

    Map<String,Object> selectSubTimes(Integer id);
}
