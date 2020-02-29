package com.dccj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dccj.dao.BaseDao;
import com.dccj.uitl.PageObject;
import com.dccj.entity.CompanyEntity;
import com.dccj.entity.CompanyPamEntity;
import com.dccj.entity.ExamineForcomEntity;

public interface CompanyDao extends BaseDao<CompanyEntity>{
	public List<CompanyEntity> findPageObjects(@Param("entity")CompanyEntity entity,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")CompanyEntity entity);
	
	public int insertexamineforcom(ExamineForcomEntity entity);
	public List<ExamineForcomEntity> findCompanyRecord(@Param("entity")ExamineForcomEntity entity,@Param("pageObj")PageObject pageObj);
	public int getRowCompanyRecord(@Param("entity")ExamineForcomEntity entity);
	public Map<String,Object>findrecordCompanyById(Integer id);
	CompanyPamEntity serchrCompanyPam();
	CompanyPamEntity serchrTPam();
	
}
