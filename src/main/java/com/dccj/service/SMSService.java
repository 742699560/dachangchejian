package com.dccj.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

public interface SMSService {
    SendSmsResponse sendSms(String code, JSONObject param, String phone) throws ClientException;
}
