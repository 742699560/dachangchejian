package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.dao.SysSmsMapper;
import com.dccj.entity.SysSms;
import com.dccj.service.SysSmsService;
@Service
public class SysSmsServiceImpl implements SysSmsService{

    @Resource
    private SysSmsMapper sysSmsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysSmsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysSms record) {
        return sysSmsMapper.insert(record);
    }

    @Override
    public int insertSelective(SysSms record) {
        return sysSmsMapper.insertSelective(record);
    }

    @Override
    public SysSms selectByPrimaryKey(Integer id) {
        return sysSmsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysSms record) {
        return sysSmsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysSms record) {
        return sysSmsMapper.updateByPrimaryKey(record);
    }

}
