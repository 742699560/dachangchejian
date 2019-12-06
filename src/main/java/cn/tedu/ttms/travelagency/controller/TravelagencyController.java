package cn.tedu.ttms.travelagency.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.travelagency.entity.ExamineEntity;
import cn.tedu.ttms.travelagency.entity.ExamineNameEntity;
import cn.tedu.ttms.travelagency.entity.ParameterTravelEntity;
import cn.tedu.ttms.travelagency.entity.TravelagencyEntity;
import cn.tedu.ttms.travelagency.service.TravelagencyService;

@Controller
@RequestMapping("/travel")
public class TravelagencyController {
	@Resource
	private TravelagencyService travelagencyService;
	@RequestMapping("travelUI")
	public String comUI(){
		return "travel/travel_list";
	}
	@RequestMapping("examineUI")
	public String examineUI(){
		return "travel/examine_list";
	}
	@RequestMapping("editexamineUI")
	public String editexamineUI(){
		return "travel/examine_edit";
	}
	
	@RequestMapping("recordUI")
	public String recordUI(){
		return "travel/record_list";
	}
	@RequestMapping("editrecordUI")
	public String editrecordUI(){
		return "travel/record_edit";
	}
	@RequestMapping("travelPamessUI")
	public String travelPamessUI(){
		return "travel/exigencypamtravel_list";
	}
	
	
	@RequestMapping("comexaminameUI")
	@ResponseBody
	public JsonResult comExamineName(){
		List<ExamineNameEntity> list = travelagencyService.comExamineName();
		return new JsonResult(list);
	}
	
	/**
	 * 跳转到编辑页面
	 */
	@RequestMapping("editTravelUI")
	public String editUser(){
		return "travel/travel_edit";
	}
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public Map<String,Object> findPageObjects(@ModelAttribute TravelagencyEntity entity){
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= travelagencyService.findPageObjects(entity,pageObject);
	    return map;
	}
	
	/**
	 * 显示执法信息
	 */
	
	@RequestMapping("/findRecordObject")
	@ResponseBody
	public Map<String,Object> findRecordObject(@ModelAttribute ExamineEntity entity){
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= travelagencyService.findRecordObject(entity,pageObject);
	    return map;
	}
	
	/**
	 * 根据id查询信息，用于回显
	 */
	@RequestMapping("findTravelById")
	@ResponseBody
	public JsonResult findUserById(Integer tid){
		Map<String, Object> map = travelagencyService.findComopanyById(tid);
		return new JsonResult(map);
	}
	@RequestMapping("doSaveTravel")
	@ResponseBody
	public JsonResult doSaveTravel(TravelagencyEntity entity){
		travelagencyService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doExobject")
	@ResponseBody
	public JsonResult Exobject(ExamineEntity entity){
		travelagencyService.saveExobject(entity);
		return new JsonResult();
	}
	@RequestMapping("doUpdateTravel")
	@ResponseBody
	public JsonResult doUpdateTravel(TravelagencyEntity entity){
		travelagencyService.updateObject(entity);
		return new JsonResult();
	}
	@RequestMapping("deleteTravel")
	@ResponseBody
	public JsonResult deleteTravel(Integer tid){
		travelagencyService.deletObject(tid);
		return new JsonResult();
	}
	
	
	
	@RequestMapping("doSaveExamine")
	@ResponseBody
	public JsonResult Exobject(ExamineNameEntity entity){
		travelagencyService.saveExname(entity);
		return new JsonResult();
	}
	@RequestMapping("doUpdateExamine")
	@ResponseBody
	public JsonResult doUpdateTravel(ExamineNameEntity entity){
		travelagencyService.updateExname(entity);
		return new JsonResult();
	}
	@RequestMapping("findExamineById")
	@ResponseBody
	public JsonResult findExamineById(Integer eid){
		Map<String,Object> map = travelagencyService.findExamineById(eid);
		return new JsonResult(map);
	}
	@RequestMapping("findrecordById")
	@ResponseBody
	public JsonResult findrecordById(Integer eid){
		Map<String,Object> map = travelagencyService.findrecordById(eid);
		return new JsonResult(map);
	}
	
	@RequestMapping("/findtravelParameterTime")
	@ResponseBody
	public ParameterTravelEntity findTravelParameterTime(){
		ParameterTravelEntity entity = travelagencyService.findTravelParameterTime();
	    return entity;
	}
	
	
	
}
