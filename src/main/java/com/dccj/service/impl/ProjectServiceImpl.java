package com.dccj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dccj.uitl.PageObject;
import com.dccj.dao.ProjectDao;
import com.dccj.entity.ParameterEntity;
import com.dccj.entity.Project;
import com.dccj.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private ProjectDao projectDao;


	public Map<String, Object>  findPageObjects(Project project, PageObject pageObj) {
		List<Project> list = projectDao.findPageObjects(project,pageObj);
		int rowCount=projectDao.getRowCount(project);
		pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	
	@Override
	public ParameterEntity findParameterTime() {
		ParameterEntity entity = projectDao.findParameterTime();
		return entity;
	}

	@Override
	public int insertProject(Project project) {
		int ret = projectDao.insertObject(project);
		return ret;
	}
	
}






