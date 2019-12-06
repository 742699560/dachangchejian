package cn.tedu.ttms.camera.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.camera.entity.Camera;
import cn.tedu.ttms.camera.service.CameraService;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;

@Controller
@RequestMapping("/camera")
public class CameraController {
	@Resource
	private CameraService cameraService;

	@RequestMapping("editUI")
	public String editUI(){
		return "video/camera_edit";
	}
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Camera entity,PageObject pageObject){
		Map<String,Object> map= cameraService.
				findPageObjects(entity,pageObject);
		return new JsonResult(map);
	}
	/**
	 * 根据id查询信息，用于回显
	 */
	@RequestMapping("findCameraById")
	@ResponseBody
	public JsonResult findCameraById(Integer cid){
		Map<String, Object> map = cameraService.findCameraById(cid);
		return new JsonResult(map);
	}
	@RequestMapping("doSaveCamera")
	@ResponseBody
	public JsonResult doSaveCamera(Camera entity){
		cameraService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doUpdateCamera")
	@ResponseBody
	public JsonResult doUpdateCamera(Camera entity){
		cameraService.updateObject(entity);
		return new JsonResult();
	}
	@RequestMapping("deleteCamera")
	@ResponseBody
	public JsonResult deleteCamera(Integer cid){
		cameraService.deletObject(cid);
		return new JsonResult();
	}
}




