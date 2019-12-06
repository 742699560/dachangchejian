package cn.tedu.ttms.attachement.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.common.web.PageObject;

@Controller
@RequestMapping("/attach")
public class AttachementController {
	@Resource
	private AttachementService attachementService;
	@RequestMapping("/uploadUI")
	public String uploadUI(){
		return "attachement/attachement";
	}


	@RequestMapping("/doFindObjects")
	@ResponseBody
	public Map<String,Object> doFindObjects(@ModelAttribute Attachement entity){
		
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= attachementService.findObjects(entity,pageObject);
	    return map;
		
	}
	@RequestMapping("/doDownload")
	@ResponseBody
	public byte[] doDownload(Integer tid,HttpServletResponse response)throws IOException{
		File file=attachementService.findObjectById(tid);
		//设置响应消息头(下载时的固定写法)
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition","attachment;filename="+file.getName());
		//根据文件真实路径构建一个Path对象　
		Path path=Paths.get(file.getPath());
		//将文件的字节返回(spring mvc 会自动将这字节写入到文件)
		return Files.readAllBytes(path);
		//return file;
	}














}
