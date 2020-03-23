package com.dccj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.util.StringUtils;

import com.dccj.exception.AppException;
import com.dccj.uitl.ObjectUtil;


@Slf4j
public class BaseController {

	
	protected Map<String, String> getParamMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> tenum = req.getParameterNames();
		while (tenum.hasMoreElements()) {
			String paramName = (String) tenum.nextElement();
			String[] values = req.getParameterValues(paramName);
			map.put(paramName, values[0]);
		}
		return map;
	}

	/**
	 * 校验请求参数是否完整
	 * 
	 * @param paramNameArr
	 *            必须存在的请求参数名数组
	 */
	protected void checkParam(String[] paramNameArr, Map<String, String> param) {
		for (String paramName : paramNameArr) {
			if (!param.containsKey(paramName))
				throw new AppException("请求参数异常!");
		}
	}

	protected Object setPojoValue(Map<String, String> param, Object obj) {
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return simpleDateFormat.parse(value.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		}, Date.class);

		List<String> fieldNames = ObjectUtil.getFiledNameList(obj);
		for (String key : param.keySet()) {
			if (fieldNames.contains(key)) {
				Object value = param.get(key);
				if (!StringUtils.isEmpty(value))
					try {
						BeanUtils.setProperty(obj, key, param.get(key));
					} catch (Exception e) {
						throw new AppException("参数值异常");
					}
			}
		}
		return obj;
	}

	public static void writeResponse(HttpServletRequest request, HttpServletResponse response, RespEntity res) {
		try {
			JSONObject json = (JSONObject)JSONObject.toJSON(res);
			String s = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue );
			response.getOutputStream().write(s.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(),ex);
		}
	}

}
