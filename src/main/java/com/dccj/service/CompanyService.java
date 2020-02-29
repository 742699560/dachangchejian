package com.dccj.service;

import java.util.Map;

import com.dccj.uitl.PageObject;
import com.dccj.entity.CompanyEntity;
import com.dccj.entity.CompanyPamEntity;
import com.dccj.entity.ExamineForcomEntity;

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
