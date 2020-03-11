package com.dccj.service;

import com.dccj.entity.CarOrder;
import com.dccj.entity.WXPayNotifyRequest;

import java.math.BigDecimal;
import java.util.Map;

public interface WxService {



    Map<String, String> getWxJsSdkData(String url);

    Map<String,Object> getOpenId(String code);

    Map<String, String> payBeforeByJSAPI(CarOrder order, BigDecimal amount) throws Exception;

    void pay(CarOrder carOrder, WXPayNotifyRequest notify);

    void sendTemplate(String data);
}
