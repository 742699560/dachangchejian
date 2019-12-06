package cn.tedu.ttms.travelagency.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.travelagency.entity.ExamineEntity;
import cn.tedu.ttms.travelagency.entity.ExamineNameEntity;
import cn.tedu.ttms.travelagency.entity.ParameterTravelEntity;
import cn.tedu.ttms.travelagency.entity.TravelagencyEntity;

public interface TravelagencyDao extends BaseDao<TravelagencyEntity>{
	public List<TravelagencyEntity> findPageObjects(@Param("entity")TravelagencyEntity entity,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")TravelagencyEntity entity);
	public int insertExobject(ExamineEntity entity);
	public List<ExamineNameEntity> comExamineName();
	public int insertExname(ExamineNameEntity entity);
	public int updateExname(@Param("entity")ExamineNameEntity entity);
	public Map<String,Object> findExamineById(Integer id);
	/**
	 * 显示执法信息
	 */
	public List<ExamineEntity> findRecordObject(@Param("entity")ExamineEntity entity,@Param("pageObj")PageObject pageObj);
	public int getRowCountRecord(@Param("entity")ExamineEntity entity);
	public Map<String,Object>findrecordById(Integer id);
	ParameterTravelEntity findTravelParameterTime();
	
}
