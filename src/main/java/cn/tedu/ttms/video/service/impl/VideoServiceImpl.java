package cn.tedu.ttms.video.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.video.dao.VideoDao;
import cn.tedu.ttms.video.entity.VideoEntity;
import cn.tedu.ttms.video.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService{

	@Resource
	private VideoDao videoDao;
	
	public Map<String, Object>  findPageObjects(VideoEntity entity,PageObject pageObj) {		
		List<VideoEntity> list = videoDao.findPageObjects(entity,pageObj);
		int rowCount=videoDao.getRowCount(entity);
		pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	@Override
	public Map<String, Object> findComopanyById(Integer vid) {
		
		if(vid==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = videoDao.findObjectById(vid);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	@Override
	public void deletObject(Integer id) {
		int rows=videoDao.deletObject(id);
		if(rows==-1)
		throw new RuntimeException("删除失败");
	}
	@Override
	public void saveObject(VideoEntity entity) {
		int rows=videoDao.insertObject(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	@Override
	public void updateObject(VideoEntity entity) {
		int rows=videoDao.updateObject(entity);
		if(rows==-1)
		throw new UpdateRuntimeException(
				"修改失败");
	}
	
	@Override
	public int findNumber(Integer id) {
		int  canmerNumber = videoDao.findNumber(id);
		return canmerNumber;
	}


}
