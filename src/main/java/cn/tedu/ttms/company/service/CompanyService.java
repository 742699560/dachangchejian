package cn.tedu.ttms.company.service;

import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.company.entity.CompanyEntity;
import cn.tedu.ttms.company.entity.CompanyPamEntity;
import cn.tedu.ttms.company.entity.ExamineForcomEntity;
public interface CompanyService {
	Map<String,Object> findPageObjects(CompanyEntity entity,PageObject pageObj);
	Map<String, Object> findComopanyById(Integer userId);
	void  saveObject(CompanyEntity entity);
	void updateObject(CompanyEntity entity);
	void deletObject(Integer id);
	
	void  saveCompanyExobject(ExamineForcomEntity entity);
	Map<String,Object> findCompanyRecord(ExamineForcomEntity entity,PageObject pageObj);
	Map<String, Object> findrecordCompanyById(Integer id);
	CompanyPamEntity findPamforCom();
}
