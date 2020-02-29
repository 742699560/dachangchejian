package com.dccj.service;

import java.util.Map;

import com.dccj.uitl.PageObject;
import com.dccj.entity.ParameterEntity;
import com.dccj.entity.Project;

public interface ProjectService {
	
	Map<String,Object> findPageObjects(Project project, PageObject pageObj);
	ParameterEntity findParameterTime();
	int insertProject(Project project);
}





