package com.dccj.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

/**
 * Created by zhaotao on 2016/4/27.
 */
public class RetailHttpServletRequest extends HttpServletRequestWrapper {

    public static final int LEN_BUFFER = 32768; //32 KB


    private byte[] cache;
    private boolean isCached = false;
    private Map<String, String> decryptParams = new HashMap<>();


    public RetailHttpServletRequest(HttpServletRequest httpServletRequest)
            throws ServletException {

        super(httpServletRequest);
        String ct = httpServletRequest.getHeader("Content-Type");
        if (StringUtils.isNotBlank(ct) && ct.trim().toLowerCase().contains("application/json")) {
            try {
                try (BufferedInputStream is = new BufferedInputStream(super.getInputStream());
                     ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[LEN_BUFFER];
                    int bytesRead = is.read(buffer);
                    while (bytesRead != -1) {
                        os.write(buffer, 0, bytesRead);
                        bytesRead = is.read(buffer);
                    }
                    cache = os.toByteArray();
                    isCached = true;
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (isCached)
            return new RetailServletInputStream(cache);
        else
            return super.getInputStream();
    }


    @Override
    public BufferedReader getReader() throws IOException {
        if (isCached) {
            String enc = getCharacterEncoding();
            if (enc == null) enc = "UTF-8";
            return new BufferedReader(new InputStreamReader(getInputStream(), enc));
        } else
            return super.getReader();
    }

    @Override
    public String getParameter(String name) {
        if (name != null && (name.equals("userID") || name.equals("userId"))) {
            String ittm = super.getParameter("ittm");
            if (StringUtils.isNoneBlank(ittm)) {
                String[] split = ittm.split("\\|");
                if (split.length != 2) {
                    return null;
                }
                return split[0];
            }
        }
        return super.getParameter(name);
    }

    public String[] getParameterValues(String name) {
        if (name.equals("userID") || name.equals("userId")) {
            return new String[]{getParameter(name)};
        }
        return super.getParameterValues(name);
    }

    public Enumeration getParameterNames() {

        Enumeration enumeration = super.getParameterNames();
        final List<Object> list = new ArrayList<Object>();
        while (enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            if (o.equals("ittm")) {
                list.add("userID");
                list.add("userId");
            }
            list.add(o);
        }


        return new Enumeration() {

            private int index = 0;

            public boolean hasMoreElements() {
                return index < list.size();
            }

            public Object nextElement() {
                return list.get(index++);
            }
        };
    }
}
