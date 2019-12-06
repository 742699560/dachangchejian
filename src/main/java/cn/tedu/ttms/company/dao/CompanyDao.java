package cn.tedu.ttms.company.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.company.entity.CompanyEntity;
import cn.tedu.ttms.company.entity.CompanyPamEntity;
import cn.tedu.ttms.company.entity.ExamineForcomEntity;

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
