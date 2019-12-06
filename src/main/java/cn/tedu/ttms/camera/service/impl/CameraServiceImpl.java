package cn.tedu.ttms.camera.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.camera.dao.CameraDao;
import cn.tedu.ttms.camera.entity.Camera;
import cn.tedu.ttms.camera.service.CameraService;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;

@Service
public class CameraServiceImpl implements CameraService{
	@Resource
	private CameraDao cameraDao;

	public Map<String, Object> findPageObjects(Camera entity,PageObject pageObject) {
		List<Camera> list=cameraDao.findPageObjects(entity,pageObject);
		int rowCount=cameraDao.getRowCount(entity);
		pageObject.setRowCount(rowCount);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	@Override
	public void deletObject(Integer id) {
		int rows=cameraDao.deletObject(id);
		if(rows==-1)
		throw new RuntimeException("删除失败");
	}
	@Override
	public Map<String, Object> findCameraById(Integer id) {

		if(id==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = cameraDao.findObjectById(id);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	@Override
	public void saveObject(Camera entity) {
		int rows=cameraDao.insertObject(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
	}
	@Override
	public void updateObject(Camera entity) {
		int rows=cameraDao.updateObject(entity);
		if(rows==-1)
		throw new UpdateRuntimeException(
				"修改失败");
	}








}






