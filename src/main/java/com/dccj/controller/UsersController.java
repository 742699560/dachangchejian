package com.dccj.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.dccj.entity.SysSms;
import com.dccj.entity.Users;
import com.dccj.exception.AppException;
import com.dccj.service.SMSService;
import com.dccj.service.SysSmsService;
import com.dccj.service.UsersService;
import com.dccj.service.WxService;
import com.dccj.service.impl.WxServiceImpl;
import com.dccj.uitl.Validation;
import com.dccj.wx.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    @Resource
    private UsersService usersService;
    @Resource
    private WxService wxService;
    @Resource
    private SysSmsService sysSmsService;
    @Resource
    private SMSService smsService;

    @RequestMapping("/registerUsers")
    @ResponseBody
    public RespEntity registerUsers(Users users) {
        usersService.insertSelective(users);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }

    @RequestMapping("/queryUsers")
    @ResponseBody
    public RespEntity queryUsers(@RequestParam(required = false) String openId, @RequestParam(required = false) String unionId, @RequestParam(required = false) Integer id) {
        if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(unionId) && id == null)
            throw new AppException("参数错误");
        Users users = usersService.selectByUnionIdAndOpenIdAndId(unionId, openId, id);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }

    @RequestMapping("/updateUsers")
    @ResponseBody
    public RespEntity updateUsers(Users users) {
        if (users.getId() == null)
            throw new AppException("参数错误");
        usersService.updateByPrimaryKeySelective(users);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }


    @RequestMapping("/getOpenId")
    @ResponseBody
    public RespEntity getOpenId(@RequestParam String code) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(code))
            throw new AppException("参数错误");
        String openId = wxService.getOpenId(code);
        // 发送请求
        JSONObject ret = new JSONObject();
        ret.put("openId", openId);
        respEntity.setData(ret);
        return respEntity;
    }

    @RequestMapping("/getWxJsConfig")
    @ResponseBody
    public RespEntity getWxJsConfig(@RequestParam String url) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(url))
            throw new AppException("参数错误");
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AppException("url解码错误");
        }
        // 发送请求
        respEntity.setData(wxService.getWxJsSdkData(url));
        return respEntity;
    }

    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public RespEntity sendVerifyCode(@RequestParam String phone) {
        RespEntity respEntity = new RespEntity();
        if (!Validation.isMobile(phone))
            throw new AppException("手机号格式错误");
        SysSms sysSms = sysSmsService.selectByPhone(phone);
        String code = new Random().nextInt(10000) + "";
        int randLength = code.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                code = "0" + code;
        }
        if (sysSms == null) {
            sysSms = new SysSms();
            sysSms.setPhone(phone);
            sysSms.setCode(code);
            sysSms.setTime(System.currentTimeMillis() / 1000 + "");
            sysSms.setUpdateTime(new Date());
            sysSms.setCreateTime(new Date());
            sysSmsService.insertSelective(sysSms);
        } else {
            if (System.currentTimeMillis() / 1000 - Long.parseLong(sysSms.getTime()) < 60)
                throw new AppException("发送短信的最小间隔时间为60秒");
            sysSms.setCode(code);
            sysSms.setTime(System.currentTimeMillis() / 1000 + "");
            sysSms.setUpdateTime(new Date());
            sysSmsService.updateByPrimaryKeySelective(sysSms);
        }
        JSONObject param = new JSONObject();
        param.put("code", code);
        try {
            smsService.sendSms("SMS_164055816", param, phone);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
            throw new AppException("短信发送失败");
        }
        return respEntity;
    }
}
