package com.dccj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dccj.entity.*;
import com.dccj.exception.AppException;
import com.dccj.service.*;
import com.dccj.uitl.DateUtils;
import com.dccj.uitl.OrderNumberUtils;
import com.dccj.uitl.Validation;
import com.dccj.wx.BeanToXml;
import com.dccj.wx.MyConfig;
import com.dccj.wx.WXPay;
import com.dccj.wx.WXPayUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    CarOrderStepService carOrderStepService;

    @Resource
    CarCenterService carCenterService;

    @Resource
    CarPriceService carPriceService;

    @Resource
    WxService wxService;

    @Resource
    SysSmsService sysSmsService;


    @RequestMapping("/queryCarTime")
    @ResponseBody
    public RespEntity queryCarTime(@RequestParam Integer stationId, @RequestParam String date) {
        RespEntity respEntity = new RespEntity();
        if (!Validation.isDate1(date))
            throw new AppException("日期格式错误");
        List<CarTime> list = carTimeService.selectByStationIdOrderByTimeSub(stationId, date);
        respEntity.putListData(list);
        return respEntity;
    }

    @RequestMapping("/queryOrder")
    @ResponseBody
    public RespEntity queryOrder(@RequestParam Integer userId) {
        RespEntity respEntity = new RespEntity();
        List<CarOrder> list = carOrderService.selectByUserIdOrderByCreateTimeDesc(userId.toString());
        respEntity.putListData(list);
        return respEntity;
    }


    @RequestMapping("/queryOrderStep")
    @ResponseBody
    public RespEntity queryOrderStep(@RequestParam Integer orderId) {
        RespEntity respEntity = new RespEntity();
        List<CarOrderStep> list = carOrderStepService.selectByOrderId(orderId);
        respEntity.putListData(list);
        return respEntity;
    }


    @RequestMapping("/createOrder")
    @ResponseBody
    public RespEntity createOrder(CarOrder carOrder) {
        RespEntity respEntity = new RespEntity();
        if (carOrder.getStationId() == null)
            throw new AppException("车检单位参数缺失");
        if (carStationService.selectByPrimaryKey(carOrder.getStationId()) == null)
            throw new AppException("车检单位参数错误");
        if (carOrder.getCarId() == null)
            throw new AppException("车辆参数缺失");
        CarCenter carCenter = carCenterService.selectByPrimaryKey(carOrder.getCarId());
        if (carCenter == null)
            throw new AppException("车辆参数错误");
        if (StringUtils.isEmpty(carOrder.getUserId()))
            throw new AppException("用户参数缺失");
        if (carOrder.getType() == null)
            throw new AppException("订单类型参数缺失");
        List<DataDir> orderTypeList = dataDirService.selectByType("carOrderType");
        if (!orderTypeList.stream().filter(t -> t.getValue().equals(carOrder.getType().toString())).findAny().isPresent())
            throw new AppException("订单类型参数错误");
        if (StringUtils.isEmpty(carOrder.getUserSign()))
            throw new AppException("用户签名参数缺失");
        CarTime carTime = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (carOrder.getType() == 2) {
            carTime = carTimeService.selectByPrimaryKey(carOrder.getTimeId());
            if (carOrder.getTimeId() == null)
                throw new AppException("预约车检必须选择预约时间段");
            if (StringUtils.isEmpty(carOrder.getTimes()))
                throw new AppException("预约车检必须选择预约日期");
            if (!Validation.isDate1(carOrder.getTimes()))
                throw new AppException("预约日期格式错误");
            if (carTime == null)
                throw new AppException("预约时间段参数数据错误");
            try {
                Date orderDate = df.parse(carOrder.getTimes());
                if (DateUtils.daysBetweenDates(orderDate, new Date()) > 15 && DateUtils.daysBetweenDates(orderDate, new Date()) <= 0) {
                    throw new AppException("只能预约今日之后15天之内的日期");
                }
            } catch (ParseException e) {
                throw new AppException("日期错误");
            }
        } else {
            String date = df.format(new Date());
            carOrder.setTimes(date);
        }

        String size = carCenter.getCarSize();
        String[] par = size.split("X");
        CarPrice carPrice = carPriceService.selectByTypeIdAndHeightFromAndHeightEnd(carCenter.getCarType(), new BigDecimal(par[0]), new BigDecimal(par[1]));
        if (carPrice == null)
            throw new AppException("未查询到相关价格信息");
        carOrder.setMobile(carCenter.getMobile());
        carOrder.setUsername(carCenter.getUsername());
        carOrder.setOrderAmount(carPrice.getPrice());
        carOrder.setOrderAmount(new BigDecimal("0.01"));
        carOrder.setStatus(10);
        carOrder.setCarNum(carCenter.getCarNum());
        carOrder.setOrderNumber(new OrderNumberUtils(1, 3).nextId() + "");
        carOrderService.createOrder(carOrder, carCenter, carTime);
        respEntity.setData(carOrder);
        return respEntity;
    }


    @RequestMapping("/wxPayBefore")
    @ResponseBody
    public RespEntity wxPayBefore(@RequestParam Integer orderId) {
        RespEntity respEntity = new RespEntity();
        CarOrder carOrder = carOrderService.selectByPrimaryKey(orderId);
        if (carOrder.getStatus() >= 25)
            throw new AppException("订单已经支付成功");
        try {
            Map<String, String> map = wxService.payBeforeByJSAPI(carOrder, carOrder.getOrderAmount());
            carOrder.setStatus(15);
            carOrderService.updateByPrimaryKeySelective(carOrder);
            respEntity.setData(map);
        } catch (Exception e) {
            if (e instanceof AppException)
                respEntity = new RespEntity(RespEntity.CODE_ERROR, e.getMessage());
            else
                respEntity = new RespEntity(RespEntity.CODE_ERROR, "微信支付处理失败");
            log.error(e.getMessage(), e);
        }
        return respEntity;
    }


    @RequestMapping(value = "/wxNotifyCallBack", consumes = "text/xml", produces = "text/xml", method = RequestMethod.POST)
    public WXPayNotifyResponse wxNotifyCallBack(@RequestBody WXPayNotifyRequest notify,
                                                HttpServletRequest request, HttpServletResponse response) {
        WXPayNotifyResponse resXml = new WXPayNotifyResponse();
        resXml.setReturnCode("SUCCESS");
        resXml.setReturnMsg("处理成功");
        try {
            String orderNumber = notify.getOutTradeNo();
            CarOrder carOrder = carOrderService.selectByOrderNumber(orderNumber);

            if (carOrder == null)
                throw new AppException("订单数据异常");
            if (carOrder.getStatus() >= 25)
                return resXml;
            wxService.pay(carOrder, notify);
        } catch (Exception e) {
            String orderNumber = notify.getOutTradeNo();
            CarOrder carOrder = carOrderService.selectByOrderNumber(orderNumber);
            carOrder.setStatus(20);
            carOrderService.updateByPrimaryKeySelective(carOrder);
            log.error("微信支付通知接口异常 notify=" + JSONObject.toJSONString(notify), e);
            resXml.setReturnCode("ERROR");
            resXml.setReturnMsg(e.getMessage());
        }
        return resXml;
    }


}
