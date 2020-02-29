package com.dccj.controller;

import com.dccj.entity.CarStation;
import com.dccj.service.CarStationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/station")
public class CarStationController {

    @Resource
    CarStationService carStationService;

    @RequestMapping("/queryStation")
    @ResponseBody
    public RespEntity queryStation(@RequestParam String name) {
        List<CarStation> list = carStationService.selectAllByNameLike(name);
        RespEntity respEntity = new RespEntity();
        respEntity.putListData(list);
        return respEntity;
    }
}
