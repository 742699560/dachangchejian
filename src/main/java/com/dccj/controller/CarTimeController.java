package com.dccj.controller;

import com.dccj.entity.CarTime;
import com.dccj.entity.CarTime;
import com.dccj.entity.User;
import com.dccj.filter.PageBean;
import com.dccj.service.CarTimeService;
import com.dccj.service.CarTimeService;
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
@RequestMapping("/carTime/")
public class CarTimeController {

    @Resource
    private CarTimeService carTimeService;

    @RequestMapping("carTimeUI")
    @RequiresPermissions("carTime:view")
    public String priceUI() {
        return "carTime/list";
    }

    @RequestMapping("carTimeEditUI")
    public String CarTimeEditUI() {
        return "carTime/edit";
    }



    @RequestMapping("/queryCarTime")
    @ResponseBody
    public RespEntity queryCarTime(@RequestParam String sidx, @RequestParam String sord, @RequestParam Integer page, @RequestParam Integer pageSize) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(sidx))
            sidx = "time_sub";
        if (page != null && pageSize != null)
            PageHelper.startPage(page, pageSize);
        User user = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
        List<CarTime> list = carTimeService.selectAllByStationId(user.getStationId(),sord,sidx);
        respEntity.setData(new PageBean<CarTime>(list));
        return respEntity;
    }

    @RequestMapping("/saveCarTime")
    @ResponseBody
    public RespEntity saveCarTime(CarTime carTime) {
        RespEntity respEntity = new RespEntity();
        User user = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
        carTime.setStationId(user.getStationId());
        carTimeService.insertSelective(carTime);
        respEntity.setData(carTime);
        return respEntity;
    }

    @RequestMapping("/updateCarTime")
    @ResponseBody
    public RespEntity updateCarTime(CarTime carTime) {
        RespEntity respEntity = new RespEntity();
        carTimeService.updateByPrimaryKeySelective(carTime);
        respEntity.setData(carTime);
        return respEntity;
    }


    @RequestMapping("/deleteCarTime")
    @ResponseBody
    public RespEntity deleteCarTime(@RequestParam Integer id) {
        RespEntity respEntity = new RespEntity();
        carTimeService.deleteByPrimaryKey(id);
        return respEntity;
    }
}
