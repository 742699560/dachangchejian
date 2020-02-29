package com.dccj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dccj.uitl.PageObject;
import com.dccj.entity.ParameterEntity;
import com.dccj.entity.Project;


public interface ProjectDao extends BaseDao<Project> {

	public List<Project> findPageObjects(@Param("project")Project project,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")Project project);
	ParameterEntity findParameterTime();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}









