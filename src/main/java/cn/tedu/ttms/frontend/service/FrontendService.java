package cn.tedu.ttms.frontend.service;

import java.text.ParseException;
import java.util.Map;

public interface FrontendService {
	 public Map<String, Object> getlxsData();
	 public Map<String,Object> getCompanyData();
	 public Map<String,Object> getWarnData(String year,int month) throws ParseException;
}
