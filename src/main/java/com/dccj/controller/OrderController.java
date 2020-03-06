package com.dccj.controller;

import com.dccj.entity.CarOrder;
import com.dccj.entity.CarTime;
import com.dccj.entity.DataDir;
import com.dccj.exception.AppException;
import com.dccj.service.*;
import com.dccj.wx.MyConfig;
import com.dccj.wx.WXPay;
import com.dccj.wx.WXPayUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Resource
    DataDirService dataDirService;

    @Resource
    CarTimeService carTimeService;

    @Resource
    CarStationService carStationService;

    @Resource
    CarOrderService carOrderService;

    @Resource
    WxService wxService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public RespEntity getWxJsConfig(CarOrder carOrder) {
        RespEntity respEntity = new RespEntity();
        if (carOrder.getStationId() == null)
            throw new AppException("车检单位参数缺失");
        if (carStationService.selectByPrimaryKey(carOrder.getStationId()) == null)
            throw new AppException("车检单位参数错误");
        if (carOrder.getCarId() == null)
            throw new AppException("车辆参数缺失");
        if (carOrder.getUserId() == null)
            throw new AppException("用户参数缺失");
        if (carOrder.getType() == null)
            throw new AppException("订单类型参数缺失");
        List<DataDir> orderTypeList = dataDirService.selectByType("carOrderType");
        if (!orderTypeList.stream().filter(t -> t.getValue().equals(carOrder.getType().toString())).findAny().isPresent())
            throw new AppException("订单类型参数错误");
        if (StringUtils.isEmpty(carOrder.getUsername()))
            throw new AppException("用户姓名参数错误");
        if (StringUtils.isEmpty(carOrder.getMobile()))
            throw new AppException("手机号参数错误");
        if (carOrder.getType() == 1) {
            if (carOrder.getTimeId() == null)
                throw new AppException("预约车检必须选择预约时间段");
            if (carOrder.getTimes() == null)
                throw new AppException("预约车检必须选择预约日期");
            if (carTimeService.selectByPrimaryKey(carOrder.getTimeId()) == null)
                throw new AppException("预约时间段参数数据错误");
        }
        carOrderService.createOrder(carOrder);
        return respEntity;
    }

    @RequestMapping("/wxPayBefore")
    @ResponseBody
    public RespEntity wxPayBefore(@RequestParam String orderId, @RequestParam BigDecimal payAmount) {
        RespEntity respEntity = new RespEntity();

        return respEntity;
    }


}
