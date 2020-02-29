package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.entity.CUser;
import com.dccj.dao.CUserMapper;
import com.dccj.service.CUserService;
@Service
public class CUserServiceImpl implements CUserService{

    @Resource
    private CUserMapper cUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CUser record) {
        return cUserMapper.insert(record);
    }

    @Override
    public int insertSelective(CUser record) {
        return cUserMapper.insertSelective(record);
    }

    @Override
    public CUser selectByPrimaryKey(Integer id) {
        return cUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public CUser selectByName(String name) {
        return cUserMapper.selectByName(name);
    }

    @Override
    public int updateByPrimaryKeySelective(CUser record) {
        return cUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CUser record) {
        return cUserMapper.updateByPrimaryKey(record);
    }

}
