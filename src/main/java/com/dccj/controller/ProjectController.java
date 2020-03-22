package com.dccj.controller;

import java.util.Map;

import javax.annotation.Resource;

import com.dccj.uitl.PageObject;
import com.dccj.entity.ParameterEntity;
import com.dccj.entity.Project;
import com.dccj.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品项目管理控制器对象
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	@Resource
	private ProjectService projectService;
	
	@RequestMapping("messageUI")
	public String comUI(){
		return "exigencymessage/exigency_list";
	}
	@RequestMapping("projectParUI")
	public String projectParUI(){
		return "exigencymessage/exigencypam_list1";
	}
	
	
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public Map<String,Object> findPageObjects(@ModelAttribute Project project){
		int page = project.getPage();
		int total = project.getTotal();
		int rows = project.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= projectService.findPageObjects(project,pageObject);
	    return map;
	}
	@RequestMapping("/findParameterTime")
	@ResponseBody
	public ParameterEntity findParameterTime(){
		ParameterEntity entity = projectService.findParameterTime();
	    return entity;
	}
	
	
}




