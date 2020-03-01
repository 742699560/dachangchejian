package com.dccj.controller;

import com.dccj.entity.CarStation;
import com.dccj.entity.DataDir;
import com.dccj.exception.AppException;
import com.dccj.service.DataDirService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataDirController {
    @Resource
    DataDirService dataDirService;

    @RequestMapping("/dataDir")
    @ResponseBody
    public RespEntity queryStation(@RequestParam String type) {
        if(StringUtils.isEmpty(type))
            throw new AppException("参数错误");
        List<DataDir> list = dataDirService.selectByType(type);
        RespEntity respEntity = new RespEntity();
        respEntity.putListData(list);
        return respEntity;
    }
}
