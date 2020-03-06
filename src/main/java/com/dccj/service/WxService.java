package com.dccj.service;

import java.util.Map;

public interface WxService {


    Map<String, String> getWxJsSdkData(String url);

    String getOpenId(String code);
}
