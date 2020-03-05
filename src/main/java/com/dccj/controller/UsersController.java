package com.dccj.controller;

import com.dccj.entity.Users;
import com.dccj.exception.AppException;
import com.dccj.service.UsersService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    @Resource
    private UsersService usersService;

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


    @RequestMapping("/getWXInfo")
    @ResponseBody
    public RespEntity getWXInfo(@RequestParam String code) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(code))
            throw new AppException("参数错误");
        String url = this.requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="
                + grantType;
        // 发送请求
        String data = HttpUtil.get(url);
        log.debug("请求地址：{}", url);
        log.debug("请求结果：{}", data);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 形如{"session_key":"6w7Br3JsRQzBiGZwvlZAiA==","openid":"oQO565cXXXXXEvc4Q_YChUE8PqB60Y"}的字符串
        respEntity.setData(json);
        return respEntity;
    }
}
