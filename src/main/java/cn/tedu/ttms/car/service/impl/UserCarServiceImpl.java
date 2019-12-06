package cn.tedu.ttms.car.service.impl;

import cn.tedu.ttms.car.service.UserCarService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tedu.ttms.car.dao.UserCarMapper;
import cn.tedu.ttms.car.entity.UserCar;

import java.util.List;

@Service
public class UserCarServiceImpl implements UserCarService {

    @Resource
    private UserCarMapper userCarMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userCarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserCar record) {
        return userCarMapper.insert(record);
    }

    @Override
    public int insertSelective(UserCar record) {
        return userCarMapper.insertSelective(record);
    }

    @Override
    public UserCar selectByPrimaryKey(Integer id) {
        return userCarMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserCar record) {
        return userCarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserCar record) {
        return userCarMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserCar> selectByAll(UserCar record) {
            return userCarMapper.selectByAll(record);
    }

}
