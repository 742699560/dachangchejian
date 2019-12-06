package cn.tedu.ttms.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.base.controller.BaseController;
import cn.tedu.ttms.common.exception.AppException;
import cn.tedu.ttms.common.web.PageObject;

/**
 * 路线备案信息上报REST
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/app/attach")
public class AppAttachementController extends BaseController {

	@Resource
	private AttachementService attachementService;

	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest req, @RequestParam Integer pageNum,
			@RequestParam Integer pageSize) {
		Map<String, String> paramsMap = this.getParamMap(req);
		if (!paramsMap.containsKey("tid"))
			throw new AppException("请求参数错误");
		Integer tid = Integer.parseInt(paramsMap.get("tid"));
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(pageNum);
		pageObject.setPageSize(pageSize);
		Attachement param = new Attachement();
		param.setTid(tid);
		Map<String, Object> dataMap = attachementService.findObjects(param, pageObject);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("data", dataMap.get("rows"));
		map.put("message", "查询成功!");
		return map;
	}

	@RequestMapping("/uploadInfo")
	@ResponseBody
	public Map<String, Object> uploadInfo(HttpServletRequest req) {
		Map<String, String> param = this.getParamMap(req);
		this.checkParam(new String[] { "tnum", "tline", "dname", "dcard", "dphone", "dcont", "ttid" }, param);
		Attachement attachement = new Attachement();
		attachement = (Attachement) this.setPojoValue(param, attachement);
		attachement.setCreatedTime(new Date());
		this.attachementService.insertProject(attachement);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("data", attachement);
		map.put("message", "上传成功!");
		return map;
	}

}
