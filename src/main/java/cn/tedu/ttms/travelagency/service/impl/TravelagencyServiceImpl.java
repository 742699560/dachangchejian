package cn.tedu.ttms.travelagency.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.travelagency.dao.TravelagencyDao;
import cn.tedu.ttms.travelagency.entity.ExamineEntity;
import cn.tedu.ttms.travelagency.entity.ExamineNameEntity;
import cn.tedu.ttms.travelagency.entity.ParameterTravelEntity;
import cn.tedu.ttms.travelagency.entity.TravelagencyEntity;
import cn.tedu.ttms.travelagency.service.TravelagencyService;
@Service
public class TravelagencyServiceImpl implements TravelagencyService{

	@Resource
	private TravelagencyDao travelagencyDao;
	
	public Map<String, Object>  findPageObjects(TravelagencyEntity entity,PageObject pageObj) {		
		List<TravelagencyEntity> list = travelagencyDao.findPageObjects(entity,pageObj);
		int rowCount=travelagencyDao.getRowCount(entity);
		if(null != pageObj)
			pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",null == pageObj ? 0 :pageObj.getPageCurrent());
		map.put("total",null == pageObj ? 0 :pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	@Override
	public Map<String, Object> findRecordObject(ExamineEntity entity, PageObject pageObj) {
		
		List<ExamineEntity> list = travelagencyDao.findRecordObject(entity, pageObj);
		int rowCount=travelagencyDao.getRowCountRecord(entity);
		pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	
	@Override
	public Map<String, Object> findComopanyById(Integer tid) {
		
		if(tid==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = travelagencyDao.findObjectById(tid);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	@Override
	public Map<String, Object> findrecordById(Integer id) {
		if(id==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = travelagencyDao.findrecordById(id);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	
	@Override
	public void deletObject(Integer id) {
		int rows=travelagencyDao.deletObject(id);
		if(rows==-1)
		throw new RuntimeException("删除失败");
	}
	@Override
	public void saveObject(TravelagencyEntity entity) {
		int rows=travelagencyDao.insertObject(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	@Override
	public void updateObject(TravelagencyEntity entity) {
		int rows=travelagencyDao.updateObject(entity);
		if(rows==-1)
		throw new UpdateRuntimeException(
				"修改失败");
	}
	@Override
	public void saveExobject(ExamineEntity entity) {
		int rows=travelagencyDao.insertExobject(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	@Override
	public List<ExamineNameEntity> comExamineName() {
		 List<ExamineNameEntity> list = travelagencyDao.comExamineName();
		return list;
	}
	
	@Override
	public void saveExname(ExamineNameEntity entity) {
		int rows=travelagencyDao.insertExname(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	@Override
	public void updateExname(ExamineNameEntity entity) {
		int rows=travelagencyDao.updateExname(entity);
		if(rows==-1)
		throw new UpdateRuntimeException(
				"修改失败");
		
	}
	
	@Override
	public Map<String,Object> findExamineById(Integer id) {
		if(id==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = travelagencyDao.findExamineById(id);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	
	@Override
	public ParameterTravelEntity findTravelParameterTime() {
		ParameterTravelEntity entity = travelagencyDao.findTravelParameterTime();
		return entity;
	}
}
