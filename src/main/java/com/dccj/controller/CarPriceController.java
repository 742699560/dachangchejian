package com.dccj.controller;

import com.dccj.service.CarPriceService;
import com.dccj.service.CarStationService;
import com.dccj.service.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("/carPrice/")
public class CarPriceController {

    @Resource
    private CarPriceService carPriceService;

    @RequestMapping("carPriceUI")
    @RequiresPermissions("carPrice:view")
    public String priceUI() {
        return "car_price/list";
    }

    @RequestMapping("carPriceEditUI")
    public String carPriceEditUI() {
        return "car_price/edit";
    }


}
