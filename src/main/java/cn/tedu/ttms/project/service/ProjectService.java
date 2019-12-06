package cn.tedu.ttms.project.service;

import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.ParameterEntity;
import cn.tedu.ttms.project.entity.Project;

public interface ProjectService {
	
	Map<String,Object> findPageObjects(Project project,PageObject pageObj);
	ParameterEntity findParameterTime();
	int insertProject(Project project);
}





