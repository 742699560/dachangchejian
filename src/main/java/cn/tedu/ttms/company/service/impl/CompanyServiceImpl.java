package cn.tedu.ttms.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.company.dao.CompanyDao;
import cn.tedu.ttms.company.entity.CompanyEntity;
import cn.tedu.ttms.company.entity.CompanyPamEntity;
import cn.tedu.ttms.company.entity.ExamineForcomEntity;
import cn.tedu.ttms.company.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService{

	@Resource
	private CompanyDao companyDao;
	
	public Map<String, Object>  findPageObjects(CompanyEntity entity,PageObject pageObj) {		
		List<CompanyEntity> list = companyDao.findPageObjects(entity,pageObj);
		int rowCount=companyDao.getRowCount(entity);
		pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	@Override
	public Map<String, Object> findComopanyById(Integer cid) {
		
		if(cid==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = companyDao.findObjectById(cid);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	@Override
	public void deletObject(Integer id) {
		int rows=companyDao.deletObject(id);
		if(rows==-1)
		throw new RuntimeException("删除失败");
	}
	@Override
	public void saveObject(CompanyEntity entity) {
		int rows=companyDao.insertObject(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	@Override
	public void updateObject(CompanyEntity entity) {
		int rows=companyDao.updateObject(entity);
		if(rows==-1)
		throw new UpdateRuntimeException(
				"修改失败");
	}
	@Override
	public Map<String, Object> findCompanyRecord(ExamineForcomEntity entity, PageObject pageObj) {
		List<ExamineForcomEntity> list = companyDao.findCompanyRecord(entity, pageObj);
		int rowCount=companyDao.getRowCompanyRecord(entity);
		pageObj.setRowCount(rowCount);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	@Override
	public Map<String, Object> findrecordCompanyById(Integer id) {
		if(id==null){
			throw new NullPointerException("查询信息，id不能为空！");
		}
		Map<String, Object> map = companyDao.findrecordCompanyById(id);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询信息失败！");
		}
		return map;
	}
	@Override
	public void saveCompanyExobject(ExamineForcomEntity entity) {
		int rows=companyDao.insertexamineforcom(entity);
	    if(rows==-1)
	    throw new RuntimeException("保存失败");
		
	}
	
	@Override
	public CompanyPamEntity findPamforCom() {
		CompanyPamEntity com  = companyDao.serchrCompanyPam();
		CompanyPamEntity trav = companyDao.serchrTPam();
		CompanyPamEntity entity = new CompanyPamEntity();
		entity.setComnumber(com.getComnumber());
		entity.setTnumber(trav.getTnumber());
		return entity;
	}
	
}
