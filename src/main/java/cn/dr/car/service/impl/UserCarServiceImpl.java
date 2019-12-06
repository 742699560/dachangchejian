package cn.dr.car.service.impl;

import cn.dr.car.service.UserCarService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.dr.car.dao.UserCarMapper;
import cn.dr.car.entity.UserCar;
@Service
public class UserCarServiceImpl implements UserCarService {

    @Resource
    private UserCarMapper userCarMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return userCarMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UserCar record) {
        return userCarMapper.insert(record);
    }

    
    public int insertSelective(UserCar record) {
        return userCarMapper.insertSelective(record);
    }

    
    public UserCar selectByPrimaryKey(Integer id) {
        return userCarMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UserCar record) {
        return userCarMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UserCar record) {
        return userCarMapper.updateByPrimaryKey(record);
    }

}
