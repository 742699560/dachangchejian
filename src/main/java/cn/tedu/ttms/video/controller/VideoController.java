package cn.tedu.ttms.video.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.video.entity.VideoEntity;
import cn.tedu.ttms.video.service.VideoService;

@Controller
@RequestMapping("/video")
public class VideoController {
	@Resource
	private VideoService videoService;
	@RequestMapping("videoUI")
	public String videoUI(){
		return "video/video_list";
	}
	/**
	 * 跳转到编辑页面
	 */
	@RequestMapping("editVideoUI")
	public String editVideoUI(){
		return "video/video_edit";
	}
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public Map<String,Object> findPageObjects(@ModelAttribute VideoEntity entity){
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= videoService.findPageObjects(entity,pageObject);
	    return map;
	}
	
	/**
	 * 根据id查询信息，用于回显
	 */
	@RequestMapping("findVideoById")
	@ResponseBody
	public JsonResult findVideoById(Integer vid){
		Map<String, Object> map = videoService.findComopanyById(vid);
		return new JsonResult(map);
	}
	@RequestMapping("doSaveVideo")
	@ResponseBody
	public JsonResult doSaveVideo(VideoEntity entity){
		videoService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doUpdateVideo")
	@ResponseBody
	public JsonResult doUpdateVideo(VideoEntity project){
		videoService.updateObject(project);
		return new JsonResult();
	}
	@RequestMapping("deleteVideo")
	@ResponseBody
	public JsonResult deleteVideo(Integer vid){
		videoService.deletObject(vid);
		return new JsonResult();
	}
	@RequestMapping("finNumber")
	@ResponseBody
	public int finNumber(Integer vid){
		int canmerNumber = videoService.findNumber(vid);
		return canmerNumber;
	}
}
