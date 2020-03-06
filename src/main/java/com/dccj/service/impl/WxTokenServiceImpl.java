package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.dao.WxTokenMapper;
import com.dccj.entity.WxToken;
import com.dccj.service.WxTokenService;

@Service
public class WxTokenServiceImpl implements WxTokenService {

    @Resource
    private WxTokenMapper wxTokenMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wxTokenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WxToken record) {
        return wxTokenMapper.insert(record);
    }

    @Override
    public int insertSelective(WxToken record) {
        return wxTokenMapper.insertSelective(record);
    }

    @Override
    public WxToken selectByPrimaryKey(Integer id) {
        return wxTokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WxToken record) {
        return wxTokenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WxToken record) {
        return wxTokenMapper.updateByPrimaryKey(record);
    }

}


