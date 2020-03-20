package com.dccj.controller;

import com.dccj.service.CarOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order/")
public class OrderManagerController {

    @Resource
    private CarOrderService carOrderService;

    @RequestMapping("orderUI")
    @RequiresPermissions("order:list:view")
    public String orderUI() {
        return "car_order/list";
    }

    @RequestMapping("orderEditUI")
    public String orderEditUI() {
        return "car_order/edit";
    }

    @RequestMapping("orderAppointmentUI")
    @RequiresPermissions("order:appointment:view")
    public String orderAppointmentUI() {
        return "car_order/appointment_list";
    }

}
