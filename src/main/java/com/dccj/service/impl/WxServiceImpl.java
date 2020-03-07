package com.dccj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dccj.entity.CarOrder;
import com.dccj.entity.Users;
import com.dccj.entity.WXPayNotifyRequest;
import com.dccj.entity.WxToken;
import com.dccj.exception.AppException;
import com.dccj.service.CarOrderService;
import com.dccj.service.UsersService;
import com.dccj.service.WxService;
import com.dccj.service.WxTokenService;
import com.dccj.wx.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Value("#{prop.wxPayKey}")
    private String wxPayKey;

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

    @Value("#{prop.wxTemplateUrl}")
    private String wxTemplateUrl;

    @Resource
    private CarOrderService carOrderService;

    @Resource
    private UsersService usersService;

    public String getAccessToken() {
        String accessToken;
        WxToken wxToken = wxTokenService.selectByPrimaryKey(1);
        if (wxToken.getAccessToken() != null && wxToken.getTokenTime().after(new Date())) {
            accessToken = wxToken.getAccessToken();
        } else {
            Map<String, Object> data = HttpUtil.get(wxPubTokenUrl + "?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
            if (data == null)
                throw new AppException("获取微信token时出错");
            if (data.containsKey("errcode"))
                throw new AppException(data.get("errmsg").toString());
            wxToken.setAccessToken(data.get("access_token").toString());
            wxToken.setTokenTime(DateUtils.addHours(new Date(), 1));
            accessToken = wxToken.getAccessToken();
            wxTokenService.updateByPrimaryKeySelective(wxToken);
        }
        return accessToken;
    }


    public Map<String, Object> getAccessUserData(String code) {
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
            wxToken.setAccessToken(data.get("access_token").toString());
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
            wxToken.setJsapiTicket(data.get("ticket").toString());
            wxToken.setJspaiTime(DateUtils.addHours(new Date(), 1));
            jsTicket = wxToken.getJsapiTicket();
        }

        wxTokenService.updateByPrimaryKeySelective(wxToken);
        Map<String, String> map = WxSignUtils.sign(jsTicket, url);
        map.put("appId", appId);
        return map;
    }

    @Override
    public String getOpenId(String code) {
        Map<String, Object> data = getAccessUserData(code);
        return data.get("openid").toString();
    }


    @Override
    public Map<String, String> payBeforeByJSAPI(CarOrder order, BigDecimal amount) throws Exception {
        Map<String, String> map = new HashMap<>();
        Users users = usersService.selectByPrimaryKey(Integer.parseInt(order.getUserId()));
        log.info("-------------组装微信支付二维码请求参数-------------");
        MyConfig config = new MyConfig(appId, wxPayMhId, wxPayKey);
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
        data.put("openid", users.getOpenId());
        data.put("product_id", "999");     //如果有多个商品则 传99999999999
        log.info("-------------调用微信统一支付接口，生成微信支付订单 data: " + JSONObject.toJSONString(data) + "-------------");
        Map<String, String> resp = wxpay.unifiedOrder(data);
        log.info("----------------组装微信支付jsApi配置数组-----------------------");
        String returnCode = resp.get("return_code");
        if (!"SUCCESS".equals(returnCode))
            throw new AppException(resp.get("return_msg"));
        String resultCode = resp.get("result_code");
        if (!"SUCCESS".equals(resultCode))
            throw new AppException(resp.get("err_code_des"));
        String prepayId = resp.get("prepay_id");
        map.put("appId", appId);
        map.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
        map.put("nonceStr", UUID.randomUUID().toString().replaceAll("-", ""));
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", "HMAC-SHA256");
        map.put("paySign", WXPayUtil.generateSignature(map, config.getKey(), WXPayConstants.SignType.HMACSHA256));
        return map;
    }

    @Override
    public void pay(CarOrder carOrder, WXPayNotifyRequest notify) {
        MyConfig config = new MyConfig(appId, wxPayMhId, appSecret);
        WXPay wxpay = null;
        Map<String, String> notifyMap;
        Date payTime;
        try {
            wxpay = new WXPay(config);
            notifyMap = WXPayUtil.xmlToMap(BeanToXml.beanToXml(notify, WXPayNotifyRequest.class));  // 转换成map
            if (wxpay.isPayResultNotifySignatureValid(notifyMap))
                throw new AppException("签名校验失败");
            if (carOrder.getOrderAmount().multiply(new BigDecimal("100")).compareTo(new BigDecimal(notify.getCashFee())) != 0)
                throw new AppException("支付金额有误");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            payTime = df.parse(notify.getTimeEnd());
        } catch (Exception e) {
            if (e instanceof AppException)
                throw new AppException(e.getMessage());
            else
                throw new AppException("微信配置加载出错");
        }
        carOrder.setPayAmount(new BigDecimal(notify.getCashFee()).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
        carOrder.setPayData(JSONObject.toJSONString(notify));
        carOrder.setPayTime(payTime);
        carOrder.setStatus(25);
        carOrderService.pay(carOrder);
    }

    @Override
    public void sendTemplate(String data) {
        HttpUtil.post(wxTemplateUrl + getAccessToken(), data);
    }

}
