package com.dccj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dccj.entity.CarOrder;
import com.dccj.entity.WxToken;
import com.dccj.exception.AppException;
import com.dccj.service.WxService;
import com.dccj.service.WxTokenService;
import com.dccj.wx.HttpUtil;
import com.dccj.wx.MyConfig;
import com.dccj.wx.WXPay;
import com.dccj.wx.WxSignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WxServiceImpl implements WxService {

    @Resource
    private WxTokenService wxTokenService;

    @Value("#{prop.wxAppId}")
    private String appId;

    @Value("#{prop.wxAppSecret}")
    private String appSecret;

    @Value("#{prop.wxPayWhId}")
    private String wxPayMhId;

    @Value("#{prop.wxGrantType}")
    private String grantType;

    @Value("#{prop.wxRequestUrl}")
    private String requestUrl;

    @Value("#{prop.wxAccessTokenUrl}")
    private String wxAccessTokenUrl;

    @Value("#{prop.wxPubTokenUrl}")
    private String wxPubTokenUrl;

    @Value("#{prop.wxJsTicketUrl}")
    private String wxJsTicketUrl;

    @Value("#{prop.wxPayCallBackUrl}")
    private String wxPayCallBackUrl;



    public Map<String, Object> getAccessData(String code) {
        String tokenUrl = this.wxAccessTokenUrl + "?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> data = HttpUtil.get(tokenUrl);
        if (data == null)
            throw new AppException("获取微信token时出错,code=" + code);
        if (data.containsKey("errcode"))
            throw new AppException(data.get("errmsg").toString());
        WxToken wxToken = wxTokenService.selectByPrimaryKey(1);
        wxToken.setAccessTokenUser(data.get("access_token").toString());
        wxToken.setRefreshTokenUser(data.get("refresh_token").toString());
        wxToken.setTokenUserTime(DateUtils.addHours(new Date(), 1));
        wxToken.setRefreshUserTime(DateUtils.addDays(new Date(), 29));
        wxTokenService.updateByPrimaryKeySelective(wxToken);
        return data;
    }

    @Override
    public Map<String, String> getWxJsSdkData(String url) {
        String accessToken = "", jsTicket = "";
        WxToken wxToken = wxTokenService.selectByPrimaryKey(1);
        if (wxToken.getAccessToken() != null && wxToken.getTokenTime().after(new Date())) {
            accessToken = wxToken.getAccessToken();
        } else {
            Map<String, Object> data = HttpUtil.get(wxPubTokenUrl + "?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
            if (data == null)
                throw new AppException("获取微信token时出错");
            if (data.containsKey("errcode"))
                throw new AppException(data.get("errmsg").toString());
            wxToken.setAccessToken("获取微信token时出错 " + data.get("access_token").toString());
            wxToken.setTokenTime(DateUtils.addHours(new Date(), 1));
            accessToken = wxToken.getAccessToken();
        }
        if (wxToken.getJsapiTicket() != null && wxToken.getJspaiTime().after(new Date())) {
            jsTicket = wxToken.getJsapiTicket();
        } else {
            Map<String, Object> data = HttpUtil.get(wxJsTicketUrl + "?access_token=" + accessToken + "&type=jsapi");
            if (data == null)
                throw new AppException("获取微信jsTicket时出错");
            if (!data.get("errcode").toString().equals("0"))
                throw new AppException(data.get("errmsg").toString());
            wxToken.setJsapiTicket("获取微信jsTicket时出错 " + data.get("ticket").toString());
            wxToken.setJspaiTime(DateUtils.addHours(new Date(), 1));
            jsTicket = wxToken.getJsapiTicket();
        }

        wxTokenService.updateByPrimaryKeySelective(wxToken);
        Map<String,String> map = WxSignUtils.sign(jsTicket, url);
        map.put("appId",appId);
        return map;
    }

    @Override
    public String getOpenId(String code) {
        Map<String, Object> data = getAccessData(code);
        return data.get("openid").toString();
    }


    public Map<String,Object> payBefore(CarOrder order, BigDecimal amount) throws Exception {
        Map<String,String> map = new HashMap<>();
        log.info("-------------组装微信支付二维码请求参数-------------");
        MyConfig config = new MyConfig(appId, wxPayMhId, appSecret);
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "大厂车检费用");
        data.put("out_trade_no", order.getOrderNumber());
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", amount.multiply(new BigDecimal("100")).intValue() + "");
        data.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress());
        data.put("notify_url", wxPayCallBackUrl);
        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
        data.put("product_id", "999");     //如果有多个商品则 传99999999999
        log.info("-------------调用微信统一支付接口，生成微信支付订单 data: " + JSONObject.toJSONString(data) + "-------------");
        Map<String, String> resp = wxpay.unifiedOrder(data);
        log.info("----------------组装微信支付jsApi配置数组-----------------------");

        String returnCode = resp.get("return_code");
        if (!"SUCCESS".equals(returnCode))
            throw new AppException(resp.get("return_msg"));
        String prepayId = resp.get("prepay_id");
        String codeUrl = resp.get("code_url");

        orderPay.setOutSn(prepayId);
        orderPay.setAmount(order.getOrderAmount());
        orderPay.setOutPayData(JSONObject.toJSONString(data));
        orderPay.setOutCodeUrl(codeUrl);
        orderPayService.updateByPrimaryKeySelective(orderPay);
        return map;
    }
}
