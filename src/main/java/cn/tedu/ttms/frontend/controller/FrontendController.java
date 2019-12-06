package cn.tedu.ttms.frontend.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.tedu.ttms.frontend.service.FrontendService;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	private static Log log = LogFactory.getLog(FrontendController.class);
	
	@Resource
	private FrontendService fs;
	
	@RequestMapping("/{Page}")
	public String jumpPage(@PathVariable("Page") String Page,HttpServletRequest req){
		
		if(Page.equals("data")){
				
			
			Map<String, Object> lxsResult = fs.getlxsData();
		    req.setAttribute("lxsData",JSON.toJSON(lxsResult.get("lxsData")));
		    
		    Map<String, Object> companyResult = fs.getCompanyData();
		    req.setAttribute("hotelData",JSON.toJSON(companyResult.get("hotelData")));
		    req.setAttribute("lyjdData",JSON.toJSON(companyResult.get("lyjdData")));
		    
		    Map<String, Object> zzChartData = new HashMap<String, Object>();
		    zzChartData.put("lxs",lxsResult.get("lxsNum"));
		    zzChartData.put("lyjd", companyResult.get("lyjdNum"));
		    zzChartData.put("hotel", companyResult.get("hotelNum"));
		    req.setAttribute("zzChartData",JSON.toJSON(zzChartData));
		    
		    
		    Map<String, Object> zxChartData = new HashMap<String, Object>();
		  
		    Calendar c = Calendar.getInstance();
		    String year = c.get(Calendar.YEAR)+"";  
		    int month = c.get(Calendar.MONTH)+1;   
		    String monthVal = month+"";
		    if(month<10){
		    	monthVal = "0"+monthVal;
		    }
		    zxChartData.put("curData", year+"年"+monthVal+"月");
		    
		    try {
		    	 zxChartData.put("warnData",fs.getWarnData(year,month));
			} catch (Exception e) {
				 log.error("警报数据获取异常",e);
			}
			 req.setAttribute("zxChartData",JSON.toJSON(zxChartData));

			
		}

		return "frontend/"+Page;
		
	}
	
}