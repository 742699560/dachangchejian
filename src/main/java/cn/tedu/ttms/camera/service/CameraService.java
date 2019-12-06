package cn.tedu.ttms.camera.service;
import java.util.Map;

import cn.tedu.ttms.camera.entity.Camera;
import cn.tedu.ttms.common.web.PageObject;
public interface CameraService {
	Map<String,Object> findPageObjects(Camera entity,PageObject pageObj);
	Map<String, Object> findCameraById(Integer userId);
	void  saveObject(Camera entity);
	void updateObject(Camera entity);
	void deletObject(Integer id);
  
}





