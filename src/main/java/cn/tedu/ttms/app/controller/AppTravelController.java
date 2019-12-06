package cn.tedu.ttms.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.ttms.common.exception.AppException;
import cn.tedu.ttms.travelagency.entity.TravelagencyEntity;
import cn.tedu.ttms.travelagency.service.TravelagencyService;

/**
 * 旅行社人员信息REST
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/app/travel")
public class AppTravelController {

	@Resource
	private TravelagencyService travelagencyService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest req, @RequestParam String user,
			@RequestParam String password) throws Exception {
		TravelagencyEntity data = queryData(user);
		if (!password.equals(data.getPasswd()))
			throw new AppException("密码错误,请从新输入");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("data", data);
		map.put("message", "登录成功!");
		return map;
	}

	@RequestMapping("/changePass")
	@ResponseBody
	public Map<String, Object> changePass(HttpServletRequest req, @RequestParam String user,
			@RequestParam String password, @RequestParam String newPassword) throws Exception {
		TravelagencyEntity data = queryData(user);
		if (!password.equals(data.getPasswd()))
			throw new AppException("原密码错误,请从新输入");
		data.setPasswd(newPassword);
		travelagencyService.updateObject(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("data", data);
		map.put("message", "密码修改成功!");
		return map;
	}

	@SuppressWarnings("unchecked")
	private TravelagencyEntity queryData(String user) throws Exception {
		TravelagencyEntity param = new TravelagencyEntity();
		param.setUser(user);
		Map<String, Object> mapTravel = travelagencyService.findPageObjects(param, null);
		if (!(boolean) mapTravel.get("Result"))
			throw new AppException("数据查询失败");
		List<TravelagencyEntity> listTravel = (List<TravelagencyEntity>) mapTravel.get("rows");
		if (0 == listTravel.size())
			throw new AppException("旅行社信息不存在,请从新检验账号");
		TravelagencyEntity data = listTravel.get(0);
		return data;
	}

}
