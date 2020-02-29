package com.dccj.servlet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhaotao on 2016/4/27.
 */
@Component
public class RetailHttpServletRequestFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(Filter.class);
    private CommonsMultipartResolver resolver = new CommonsMultipartResolver();

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        resolver.setServletContext(request.getSession().getServletContext());
        BodyCachingHttpServletResponseWrapper responseWrapper =
                new BodyCachingHttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String contentType = servletRequest.getContentType();
        if (resolver.isMultipart(request)) {
            request = resolver.resolveMultipart(request);
            filterChain.doFilter(request, responseWrapper);
        } else if (StringUtils.isNotBlank(contentType) && contentType.toLowerCase().contains("multipart/form-data")) {
            // 对于文件上传不预先读取内容
            filterChain.doFilter(servletRequest, responseWrapper);
        } else {
            // RetailHttpServletRequest 会将内容一次行读入内存, 文件上传时不要使用
            filterChain.doFilter(new RetailHttpServletRequest((HttpServletRequest) servletRequest), responseWrapper);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {
    }
}
