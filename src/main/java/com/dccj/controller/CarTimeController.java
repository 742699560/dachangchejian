package com.dccj.controller;

import com.dccj.service.CarPriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/carTime/")
public class CarTimeController {

    @Resource
    private CarPriceService carPriceService;

    @RequestMapping("carTimeUI")
    @RequiresPermissions("carTime:view")
    public String priceUI() {
        return "car_time/list";
    }

    @RequestMapping("carTimeEditUI")
    public String carPriceEditUI() {
        return "car_time/edit";
    }


}
