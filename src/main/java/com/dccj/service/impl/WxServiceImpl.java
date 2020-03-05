package com.dccj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dccj.exception.AppException;
import com.dccj.service.WxService;
import com.dccj.wx.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxServiceImpl implements WxService {

    private String accessToken;
    private String refreshToken;

    @Value("#{prop.wxAppId}")
    private String appId;

    @Value("#{prop.wxAppSecret}")
    private String appSecret;

    @Value("#{prop.wxGrantType}")
    private String grantType;

    @Value("#{prop.wxRequestUrl}")
    private String requestUrl;

    @Value("#{prop.wxAccessTokenUrl}")
    private String wxAccessTokenUrl;

    private Map<String, Object> getAccessToken(String code) {
        String tokenUrl = this.wxAccessTokenUrl + "?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> data = HttpUtil.get(tokenUrl);
        if(data == null)
            throw new AppException("获取微信token时出错,code="+code);
        if(!StringUtils.isEmpty(data.get("access_token").toString()))
            this.accessToken = data.get("access_token").toString();
        if(!StringUtils.isEmpty(data.get("refresh_token").toString()))
            this.refreshToken = data.get("refresh_token").toString();
        return data;
    }

    public String getOpenId(String code) {
        Map<String, Object> data = getAccessToken(code);
        return data.get("openid").toString();
    }

    public String getSigin(){

    }
}
