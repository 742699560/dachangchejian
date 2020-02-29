package com.dccj.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dccj.servlet.BodyCachingHttpServletResponseWrapper;
import com.dccj.uitl.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaotao on 09/12/2015.
 */
@Slf4j
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("beginTime", System.currentTimeMillis() + "");
        Map<String, String> params = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String pname = paramNames.nextElement();
            if (pname.equals("sign") || pname.equals("timestamp"))
                continue;
            params.put(pname, request.getParameter(pname));
        }
        log.info("收到新请求 requestURI【" + request.getRequestURI() + "】 method【" + request.getMethod() + "】 IP【" + IpUtil.getIpAddr(request) + "】 params【" + JSONObject.toJSONString(params) + "】");

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long beginTime = Long.valueOf(response.getHeader("beginTime"));
        Long endTime = System.currentTimeMillis();
        BodyCachingHttpServletResponseWrapper responseWrapper = null;
        if (response instanceof ShiroHttpServletResponse) {
            ShiroHttpServletResponse shiroHttpServletResponse = (ShiroHttpServletResponse) response;
            responseWrapper = (BodyCachingHttpServletResponseWrapper) shiroHttpServletResponse.getResponse();
        } else
            responseWrapper = (BodyCachingHttpServletResponseWrapper) response;
        String result = responseWrapper.getBody();
        if (result.length() > 200)
            result = result.substring(0, 200) + ".....";
        log.info("请求处理结束 requestURI【" + request.getRequestURI() + "】 method【" + request.getMethod() + "】 耗时【" + (endTime - beginTime) + "】毫秒  result【" + result + "】");
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }

}
