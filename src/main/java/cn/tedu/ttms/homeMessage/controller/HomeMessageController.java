package cn.tedu.ttms.homeMessage.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.homeMessage.entity.HomeMessageEntity;
import cn.tedu.ttms.homeMessage.service.HomeMessageService;

@Controller
@RequestMapping("/homemessage")
public class HomeMessageController {

	@Resource
	private HomeMessageService homeMessageService;

	@RequestMapping("/findAboutprove")
	@ResponseBody
	public JsonResult findAboutprove(Integer currentPage){
		Map<String,Object> map = homeMessageService.findAboutprove(currentPage);
		return new JsonResult(map);
	}
	@RequestMapping("/findDueapprove")
	@ResponseBody
	public JsonResult findDueapprove(Integer currentPage){
		Map<String,Object> map = homeMessageService.findDueapprove(currentPage);

		return new JsonResult(map);
	}
	@RequestMapping("/findPageNumber")
	@ResponseBody
	public HomeMessageEntity findPageNumber(){
		HomeMessageEntity homeMessageEntity = homeMessageService.findPageNumber();
		return homeMessageEntity;
	}



}
