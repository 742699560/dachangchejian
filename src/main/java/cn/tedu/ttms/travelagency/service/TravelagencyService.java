package cn.tedu.ttms.travelagency.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.travelagency.entity.ExamineEntity;
import cn.tedu.ttms.travelagency.entity.ExamineNameEntity;
import cn.tedu.ttms.travelagency.entity.ParameterTravelEntity;
import cn.tedu.ttms.travelagency.entity.TravelagencyEntity;

public interface TravelagencyService {
	Map<String,Object> findPageObjects(TravelagencyEntity entity,PageObject pageObj);
	Map<String, Object> findComopanyById(Integer userId);
	void  saveObject(TravelagencyEntity entity);
	void  saveExobject(ExamineEntity entity);
	void  saveExname(ExamineNameEntity entity);
	void updateObject(TravelagencyEntity entity);
	void updateExname(ExamineNameEntity entity);
	void deletObject(Integer id);
	public List<ExamineNameEntity> comExamineName();
	Map<String,Object>findExamineById(Integer id);
	/**
	 * 显示执法
	 */
	Map<String,Object> findRecordObject(ExamineEntity entity,PageObject pageObj);
	Map<String, Object> findrecordById(Integer id);

	ParameterTravelEntity findTravelParameterTime();
	
}
