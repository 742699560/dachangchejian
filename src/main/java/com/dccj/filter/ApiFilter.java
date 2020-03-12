package com.dccj.filter;

import com.alibaba.druid.util.StringUtils;
import com.dccj.controller.RespEntity;
import com.dccj.uitl.HttpResponseUtil;
import com.dccj.uitl.MD5Util;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class ApiFilter implements Filter {
    //api接口校验sign参数是否合法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String salt = "35e5c1b47ce3";
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String sign = httpRequest.getParameter("sign");

        String timestamp = httpRequest.getParameter("timestamp");

        if(StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)){
            RespEntity res = new RespEntity();
            res.setStatus(RespEntity.CODE_SIGN_ERROR);
            HttpResponseUtil.writeResponse(httpRequest, response, res);
            return;
        }
        if(!MD5Util.md5(timestamp + salt).equals(sign)){
            RespEntity res = new RespEntity();
            res.setStatus(RespEntity.CODE_SIGN_ERROR);
            HttpResponseUtil.writeResponse(httpRequest, response, res);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    public static void main(String[] args){
        System.out.println(MD5Util.md5("1" + "35e5c1b47ce3"));
    }
}
