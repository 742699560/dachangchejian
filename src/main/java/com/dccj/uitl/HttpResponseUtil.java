package com.dccj.uitl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dccj.controller.RespEntity;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpResponseUtil {

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response, RespEntity res) {
        try {
            JSONObject json = (JSONObject) JSONObject.toJSON(res);
            String s = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
            response.getOutputStream().write(s.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
        }
    }
}
