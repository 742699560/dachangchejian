package com.dccj.controller;

import com.dccj.entity.CarCenter;
import com.dccj.entity.CompanyEntity;
import com.dccj.entity.UserCar;
import com.dccj.exception.AppException;
import com.dccj.service.CarCenterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarManagerController extends BaseController {

    @Resource
    CarCenterService carCenterService;

    @RequestMapping("/queryCars")
    @ResponseBody
    public RespEntity queryCars(@RequestParam Integer userId) {
        List<CarCenter> list = carCenterService.selectAllByUserId(userId);
        RespEntity respEntity = new RespEntity();
        respEntity.putListData(list);
        return respEntity;
    }

    @RequestMapping("/updateCars")
    @ResponseBody
    public RespEntity updateCars(CarCenter carCenter) {
        RespEntity respEntity = new RespEntity();
        if (carCenter.getId() == null)
            throw new AppException("参数错误");
        carCenterService.updateByPrimaryKeySelective(carCenter);
        respEntity.setData(carCenter);
        return respEntity;
    }

    @RequestMapping("/delCars")
    @ResponseBody
    public RespEntity delCars(@RequestParam Integer id) {
        carCenterService.deleteByPrimaryKey(id);
        RespEntity respEntity = new RespEntity();
        return respEntity;
    }


    @RequestMapping("/readCarInfo")
    @ResponseBody
    public RespEntity readCarInfo(@RequestParam Integer id) {
        carCenterService.deleteByPrimaryKey(id);
        RespEntity respEntity = new RespEntity();
        return respEntity;
    }


}
