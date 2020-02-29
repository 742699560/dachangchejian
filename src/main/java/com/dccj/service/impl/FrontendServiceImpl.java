package com.dccj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.dccj.dao.FrontendDao;
import com.dccj.service.FrontendService;
import org.springframework.stereotype.Service;

@Service
public class FrontendServiceImpl implements FrontendService {

	@Resource
	private FrontendDao fd;
	
	@Override
	public Map<String, Object> getlxsData() {
		
		List<Map<String, Object>> data = fd.getlxsData();
		List<Map<String, Object>> newData = new ArrayList<Map<String, Object>>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		if(data.size()>0){
			for(Map<String, Object> val:data){
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("name", val.get("name").toString());
				List<Object> valArr = new ArrayList<Object>();
				valArr.add(val.get("lng"));
				valArr.add(val.get("lat"));
				mp.put("value", valArr);
				newData.add(mp);
			}
		}
		returnData.put("lxsData", newData);
		returnData.put("lxsNum",newData.size());
		return returnData;
	}

	@Override
	public Map<String, Object> getCompanyData() {
		
		List<Map<String, Object>> data = fd.getCompanyData();
		List<Map<String, Object>> hotelData = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lyjdData = new ArrayList<Map<String, Object>>();
		Map<String, Object> newData = new HashMap<String, Object>();
		if(data.size()>0){
			for(Map<String, Object> val:data){
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("name",val.get("name").toString());
				List<Object> valArr = new ArrayList<Object>();
				valArr.add(val.get("lng"));
				valArr.add(val.get("lat"));
				mp.put("value", valArr);
				if(val.get("type").toString().equals("1")){
					hotelData.add(mp);
				}else{
					lyjdData.add(mp);
				}
			}
		}
		newData.put("hotelData",hotelData);
		newData.put("hotelNum", hotelData.size());
		newData.put("lyjdData",lyjdData);
		newData.put("lyjdNum", lyjdData.size());
		return newData;
	}

	@Override
	public Map<String, Object> getWarnData(String year,int month) throws ParseException {
		
		    Map<String, Object> warnData = new HashMap<String, Object>();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		    List<Object> monthData = new ArrayList<Object>();
		    List<Object> normalData = new ArrayList<Object>();
		    List<Object> seriousData = new ArrayList<Object>();
		    List<Object> errorData = new ArrayList<Object>();
		    
	    
	    		
				    for(int i=1;i<=month;i++){
				    	
				    	String mtVal = i+"";
			    	    if(i<10){
			    	    	mtVal = "0"+mtVal;
					    }
				    	
			    	    
				    	String curDate = year+"-"+mtVal;
				    	Date date = sdf.parse(curDate);
				    	
				    	for(int j=1;j<=3;j++){
				    		int num = fd.getWarnNumByYM(date,j);
				    		switch(j){
				    			case 1:normalData.add(num);break;
				    			case 2:seriousData.add(num);break;
				    			case 3:errorData.add(num);break;
				    		}

				    	}
				    	
			    	    monthData.add(mtVal+"月");
				    
				    }

	    	    

			    warnData.put("normalData", normalData);
			    warnData.put("seriousData", seriousData);
			    warnData.put("errorData", errorData);
			    warnData.put("monthData", monthData);
	
		  	    return warnData;
	}

}