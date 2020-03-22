package com.dccj.controller;

import com.dccj.entity.CarCenter;
import com.dccj.entity.CarOrder;
import com.dccj.entity.CarTime;
import com.dccj.entity.User;
import com.dccj.filter.PageBean;
import com.dccj.service.CarCenterService;
import com.dccj.service.CarOrderService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order/")
public class OrderManagerController {

    @Resource
    private CarOrderService carOrderService;
    @Resource
    private CarCenterService carCenterService;

    @RequestMapping("orderUI")
    @RequiresPermissions("order:list:view")
    public String orderUI() {
        return "carOrder/nowList";
    }

    @RequestMapping("orderEditUI")
    public String orderEditUI() {
        return "carOrder/edit";
    }

    @RequestMapping("orderAppointmentUI")
    @RequiresPermissions("order:appointment:view")
    public String orderAppointmentUI() {
        return "carOrder/appointmentList";
    }


    @RequestMapping("/queryOrder")
    @ResponseBody
    public RespEntity queryOrder(@RequestParam(required = false) String dateBegin,
                                 @RequestParam(required = false) String dateEnd,
                                 @RequestParam(required = false) String userName,
                                 @RequestParam(required = false) String mobile,
                                 @RequestParam(required = false) String carNum,
                                 @RequestParam(required = false) Integer status,
                                 @RequestParam(required = false, defaultValue = "1") Integer type,
                                 @RequestParam String sidx, @RequestParam String sord, @RequestParam Integer page, @RequestParam Integer pageSize) {
        RespEntity respEntity = new RespEntity();
        User user = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
        if (StringUtils.isEmpty(sidx))
            sidx = "create_time";
        if (page != null && pageSize != null)
            PageHelper.startPage(page, pageSize);
        List<CarOrder> list = carOrderService.selectAllByStatusAndUsernameAndMobileAndCarNumAndStationId(dateBegin, dateEnd, status, userName, mobile, carNum, user.getStationId(), type, sord, sidx);
        respEntity.setData(new PageBean<CarOrder>(list));
        return respEntity;
    }

    @RequestMapping("/queryCarCenter")
    @ResponseBody
    public RespEntity queryCarCenter(@RequestParam Integer carId) {
        RespEntity respEntity = new RespEntity();
        CarCenter carCenter = carCenterService.selectByPrimaryKey(carId);
        respEntity.setData(carCenter);
        return respEntity;
    }

    @RequestMapping("/deleteCarOrder")
    @ResponseBody
    public RespEntity deleteCarOrder(@RequestParam Integer id) {
        RespEntity respEntity = new RespEntity();
        carOrderService.deleteByPrimaryKey(id);
        return respEntity;
    }


}
