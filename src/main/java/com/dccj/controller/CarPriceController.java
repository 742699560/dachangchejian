package com.dccj.controller;

import com.dccj.entity.CarOrderStep;
import com.dccj.entity.CarPrice;
import com.dccj.entity.CarStation;
import com.dccj.entity.User;
import com.dccj.filter.PageBean;
import com.dccj.service.CarPriceService;
import com.dccj.service.CarStationService;
import com.dccj.service.CompanyService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/carPrice/")
public class CarPriceController {

    @Resource
    private CarPriceService carPriceService;

    @RequestMapping("carPriceUI")
    @RequiresPermissions("carPrice:view")
    public String priceUI() {
        return "carPrice/list";
    }

    @RequestMapping("carPriceEditUI")
    public String carPriceEditUI() {
        return "carPrice/edit";
    }


    @RequestMapping("/queryByCarType")
    @ResponseBody
    public RespEntity queryByCarType(@RequestParam(required = false) String carType, @RequestParam String sidx, @RequestParam String sord, @RequestParam Integer page, @RequestParam Integer pageSize) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(sidx))
            sidx = "id";
        if (page != null && pageSize != null)
            PageHelper.startPage(page, pageSize);
        User user = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
        List<CarPrice> list = carPriceService.selectByCarType(carType, sord, sidx,user.getStationId() );
        respEntity.setData(new PageBean<CarPrice>(list));
        return respEntity;
    }

    @RequestMapping("/saveCarPrice")
    @ResponseBody
    public RespEntity saveCarPrice(CarPrice carPrice) {
        RespEntity respEntity = new RespEntity();
        User user = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
        carPrice.setStationId(user.getStationId());
        carPriceService.insertSelective(carPrice);
        respEntity.setData(carPrice);
        return respEntity;
    }

    @RequestMapping("/updateCarPrice")
    @ResponseBody
    public RespEntity updateCarPrice(CarPrice carPrice) {
        RespEntity respEntity = new RespEntity();
        carPriceService.updateByPrimaryKeySelective(carPrice);
        respEntity.setData(carPrice);
        return respEntity;
    }


    @RequestMapping("/deleteCarPrice")
    @ResponseBody
    public RespEntity deleteCarPrice(@RequestParam Integer id) {
        RespEntity respEntity = new RespEntity();
        carPriceService.deleteByPrimaryKey(id);
        return respEntity;
    }

}
