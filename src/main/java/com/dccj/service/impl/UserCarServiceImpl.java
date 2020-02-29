package com.dccj.service.impl;

import com.dccj.service.UserCarService;
import com.dccj.uitl.PageObject;
import com.dccj.entity.UserCar;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.dao.UserCarMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> selectSubTimes(Integer id) {
        return userCarMapper.selectSubTimes(id);
    }

    @Override
    public Map<String, Object> selectByAllByPage(UserCar userCar, PageObject pageObject) {
        List<UserCar> list = userCarMapper.selectByAllByPage(userCar,pageObject);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("rows", list);
        map.put("page",pageObject.getStartIndex());
        map.put("Result", true);
        return map;
    }

}


