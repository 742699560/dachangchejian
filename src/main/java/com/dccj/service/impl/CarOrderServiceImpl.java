package com.dccj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.dccj.entity.*;
import com.dccj.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.dccj.dao.CarOrderMapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarOrderServiceImpl implements CarOrderService {

    @Resource
    private CarOrderMapper carOrderMapper;
    @Resource
    private CarOrderStepService carOrderStepService;
    @Resource
    WxService wxService;
    @Resource
    UsersService usersService;
    @Resource
    DataDirService dataDirService;
    @Resource
    SMSService smsService;
    @Resource
    CarTimeService carTimeService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarOrder record) {
        return carOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(CarOrder record) {
        return carOrderMapper.insertSelective(record);
    }

    @Override
    public CarOrder selectByPrimaryKey(Integer id) {
        return carOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarOrder record) {
        return carOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarOrder record) {
        return carOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public CarOrder createOrder(CarOrder carOrder, CarCenter carCenter, CarTime carTime) {
        carOrder.setCreateTime(new Date());
        carOrder.setUpdateTime(new Date());
        this.insertSelective(carOrder);
        List<DataDir> list = dataDirService.selectByType("carOrderStep");
        list.sort(Comparator.comparing(person -> person.getValue()));
        for (int i = 0; i < list.size(); i++) {
            DataDir item = list.get(i);
            CarOrderStep carOrderStep = new CarOrderStep();
            carOrderStep.setOrderId(carOrder.getId());
            carOrderStep.setUserId(Integer.parseInt(carOrder.getUserId()));
            carOrderStep.setName(item.getName());
            carOrderStep.setStatus(0);
            carOrderStep.setCreateTime(new Date());
            carOrderStep.setUpdateTime(new Date());
            carOrderStep.setStep(Integer.parseInt(item.getValue()));
            carOrderStepService.insertSelective(carOrderStep);
        }
        carOrderStepService.nextStep(carOrder, 10, 9999999, 1);
        sendWxOrderCommitTemplate(carOrder, carCenter, carTime);
        return carOrder;
    }

    private void sendWxOrderCommitTemplate(CarOrder carOrder, CarCenter carCenter, CarTime carTime) {
        Users users = usersService.selectByPrimaryKey(Integer.parseInt(carOrder.getUserId()));
        JSONObject data = new JSONObject();
        data.put("touser", users.getOpenId());
        data.put("template_id", "Ty-XkNhfYJuZUd3QQC7GiJleW8c4N445Zjs8V709bYM");
        data.put("url", "");
        String firstStr = "", remarkStr = "";
        if (carOrder.getType() == 2) {
            firstStr = "您的预约检车信息已经提交，请及时进行支付，支付成功后预约生效，延时支付可能导致您预约失败，无法支付请您重新上传车辆信息提交预约申请。";
            remarkStr = "您的预约车辆【" + carCenter.getCarNum() + "】车检时间【" + carOrder.getTimes() + " " + carTime.getTimeSub() + "】";
        } else {
            firstStr = "您的车检信息已提交，请及时完成支付";
            remarkStr = "您的车辆【" + carCenter.getCarNum() + "】车检时间【" + carOrder.getTimes() + "】";
        }
        JSONObject data2 = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", firstStr);
        first.put("color", "#173177");
        data2.put("first", first);
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", carCenter.getCarNum());
        keyword1.put("color", "#173177");
        data2.put("keyword1", keyword1);
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "已提交，未支付");
        keyword2.put("color", "#FF0000");
        data2.put("keyword2", keyword2);
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", remarkStr);
        keyword3.put("color", "#173177");
        data2.put("keyword3", keyword3);
        JSONObject remark = new JSONObject();
        remark.put("value", "如有疑问，请拨打咨询热线0316-8961818 / 15127683999");
        remark.put("color", "#173177");
        data2.put("remark", remark);
        data.put("data", data2);
        wxService.sendTemplate(data.toJSONString());
    }

    @Override
    public CarOrder selectByOrderNumber(String orderNumber) {
        return carOrderMapper.selectByOrderNumber(orderNumber);
    }


    @Override
    public CarOrder pay(CarOrder carOrder) {
        carOrderMapper.updateByPrimaryKeySelective(carOrder);
        carOrderStepService.nextStep(carOrder, 25, 9999999, 1);
        JSONObject param = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String time = df.format(new Date());
        param.put("time", time);
        try {
            smsService.sendSms("SMS_181859950", param, carOrder.getMobile());
        } catch (ClientException e) {
            log.error("支付短信发送失败", e);
        }
        sendWxOrderPayTemplate(carOrder);
        return carOrder;
    }

    @Override
    public List<CarOrder> selectByUserIdOrderByCreateTimeDesc(String userId) {
        return carOrderMapper.selectByUserIdOrderByCreateTimeDesc(userId);
    }

    private void sendWxOrderPayTemplate(CarOrder carOrder) {
        Users users = usersService.selectByPrimaryKey(Integer.parseInt(carOrder.getUserId()));
        JSONObject data = new JSONObject();
        data.put("touser", users.getOpenId());
        data.put("template_id", "Ty-XkNhfYJuZUd3QQC7GiJleW8c4N445Zjs8V709bYM");
        data.put("url", "");
        String firstStr = "";
        if (carOrder.getType() == 2) {
            CarTime carTime = carTimeService.selectByPrimaryKey(carOrder.getTimeId());
            firstStr = "您的车检信息已支付完成请在预约时间 【" + carOrder.getTimes() + " " + carTime.getTimeSub() + "】 前到达大厂车检所！凭此通知到收费处换取缴费凭证！";
        } else {
            firstStr = "您的车检已支付成功，请凭此通知到现场自助单据打印处领取支付凭证。";
        }
        JSONObject data2 = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", firstStr);
        first.put("color", "#173177");
        data2.put("first", first);
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", carOrder.getCarNum());
        keyword1.put("color", "#173177");
        data2.put("keyword1", keyword1);
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "已支付完成");
        keyword2.put("color", "#FF0000");
        data2.put("keyword2", keyword2);
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", "车检支付完成,共支付金额" + carOrder.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "元");
        keyword3.put("color", "#173177");
        data2.put("keyword3", keyword3);
        JSONObject remark = new JSONObject();
        remark.put("value", "如有疑问，请拨打咨询热线0316-8961818 / 15127683999");
        remark.put("color", "#173177");
        data2.put("remark", remark);
        data.put("data", data2);
        wxService.sendTemplate(data.toJSONString());
    }

    @Override
    public List<CarOrder> selectByCarId(Integer carId) {
        return carOrderMapper.selectByCarId(carId);
    }
}






