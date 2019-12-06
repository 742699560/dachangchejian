package cn.tedu.ttms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.ParameterEntity;
import cn.tedu.ttms.project.entity.Project;


public interface ProjectDao extends BaseDao<Project> {

	public List<Project> findPageObjects(@Param("project")Project project,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")Project project);
	ParameterEntity findParameterTime();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}









