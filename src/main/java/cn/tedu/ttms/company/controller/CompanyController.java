package cn.tedu.ttms.company.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.company.entity.CompanyEntity;
import cn.tedu.ttms.company.entity.CompanyPamEntity;
import cn.tedu.ttms.company.entity.ExamineForcomEntity;
import cn.tedu.ttms.company.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Resource
	private CompanyService companyService;
	@RequestMapping("companyUI")
	public String comUI(){
		return "company/company_list";
	}
	@RequestMapping("examinecomUI")
	public String examinecomUI(){
		return "company/examinecompany_list";
	}
	@RequestMapping("editrecordcomUI")
	public String editrecordcomUI(){
		return "company/recordcompany_list";
	}
	@RequestMapping("editrecordforcomUI")
	public String editrecordforcomUI(){
		return "company/recordeditcom_edit";
	}
	@RequestMapping("TravelcomforPamnumberUI")
	public String TravelcomforPamnumberUI(){
		return "company/pamcom_list";
	}
	
	
	/**
	 * 跳转到编辑页面
	 */
	@RequestMapping("editCompanyUI")
	public String editUser(){
		return "company/company_edit";
	}
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public Map<String,Object> findPageObjects(@ModelAttribute CompanyEntity entity){
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= companyService.findPageObjects(entity,pageObject);
	    return map;
	}
	
	/**
	 * 根据id查询信息，用于回显
	 */
	@RequestMapping("findCompanyById")
	@ResponseBody
	public JsonResult findUserById(Integer cid){
		Map<String, Object> map = companyService.findComopanyById(cid);
		return new JsonResult(map);
	}
	@RequestMapping("doSaveCompany")
	@ResponseBody
	public JsonResult doSaveCompany(CompanyEntity entity){
		companyService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doUpdateCompany")
	@ResponseBody
	public JsonResult doUpdateCompany(CompanyEntity project){
		companyService.updateObject(project);
		return new JsonResult();
	}
	@RequestMapping("deleteCompany")
	@ResponseBody
	public JsonResult deleteCompany(Integer cid){
		companyService.deletObject(cid);
		return new JsonResult();
	}
	@RequestMapping("/findCompanyRecord")
	@ResponseBody
	public Map<String,Object> findCompanyRecord(@ModelAttribute ExamineForcomEntity entity){
		int page = entity.getPage();
		int total = entity.getTotal();
		int rows = entity.getRows();
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(page);
		pageObject.setRowCount(total);
		pageObject.setPageSize(rows);
		Map<String,Object> map= companyService.findCompanyRecord(entity, pageObject);
	    return map;
	}
	@RequestMapping("saveCompanyExobject")
	@ResponseBody
	public JsonResult saveCompanyExobject(ExamineForcomEntity entity){
		companyService.saveCompanyExobject(entity);
		return new JsonResult();
	}
	@RequestMapping("findrecordCompanyById")
	@ResponseBody
	public JsonResult findrecordCompanyById(Integer eid){
		Map<String,Object> map = companyService.findrecordCompanyById(eid);
		return new JsonResult(map);
	}
	@RequestMapping("/findComtnumberPar")
	@ResponseBody
	public CompanyPamEntity findComtnumberPar(){
		CompanyPamEntity entity = companyService.findPamforCom();
	    return entity;
	}
	
}
