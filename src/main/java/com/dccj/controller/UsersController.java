package com.dccj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dccj.entity.Users;
import com.dccj.exception.AppException;
import com.dccj.service.UsersService;
import com.dccj.service.WxService;
import com.dccj.service.impl.WxServiceImpl;
import com.dccj.wx.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    @Resource
    private UsersService usersService;
    @Resource
    private WxService wxService;

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
}
