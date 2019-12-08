package cn.tedu.ttms.car.dao;
import java.util.List;
import java.util.Map;

import cn.tedu.ttms.car.entity.UserCar;

public interface UserCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCar record);

    int insertSelective(UserCar record);

    UserCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCar record);

    int updateByPrimaryKey(UserCar record);

    List<UserCar> selectByAll(UserCar userCar);

    Map<String,Object>  selectSubTimes(Integer id);
}