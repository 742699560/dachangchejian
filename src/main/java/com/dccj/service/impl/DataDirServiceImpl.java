package com.dccj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dccj.dao.DataDirMapper;
import com.dccj.entity.DataDir;
import com.dccj.service.DataDirService;

import java.util.List;

@Service
public class DataDirServiceImpl implements DataDirService{

    @Resource
    private DataDirMapper dataDirMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dataDirMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataDir record) {
        return dataDirMapper.insert(record);
    }

    @Override
    public int insertSelective(DataDir record) {
        return dataDirMapper.insertSelective(record);
    }

    @Override
    public DataDir selectByPrimaryKey(Integer id) {
        return dataDirMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DataDir record) {
        return dataDirMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DataDir record) {
        return dataDirMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DataDir> selectByType(String type) {
        return dataDirMapper.selectByType(type);
    }
}
