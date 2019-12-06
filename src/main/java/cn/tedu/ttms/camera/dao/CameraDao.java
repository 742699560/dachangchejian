package cn.tedu.ttms.camera.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.camera.entity.Camera;
import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;


public interface CameraDao extends BaseDao<Camera> {

	public List<Camera> findPageObjects(@Param("entity")Camera entity,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")Camera entity);
	
	
}

