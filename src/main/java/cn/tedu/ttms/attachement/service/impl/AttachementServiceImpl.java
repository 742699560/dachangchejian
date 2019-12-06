package cn.tedu.ttms.attachement.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.common.web.PageObject;

@Service
public class AttachementServiceImpl implements AttachementService{

	
	@Resource
	private AttachementDao attachementDao;
	
	@Override
	public Map<String,Object> findObjects(Attachement entity,PageObject pageObj) {
		List<Attachement> list = attachementDao.findPageObjects(entity,pageObj);
		int rowCount=attachementDao.getRowCount(entity);
		pageObj.setRowCount(rowCount);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObj.getPageCurrent());
		map.put("total",pageObj.getPageCount());
		map.put("Result", true);
		return map;
	}
	
	@Override
	public File findObjectById(Integer id) {
		
		if(id==null)
		throw new RuntimeException("id can not be null");
		//1.根据id查找记录　	
		Attachement a=attachementDao.findAttachementById(id);
		if(a==null)
		throw new RuntimeException("数据库中没有对应的记录信息");
		//2.获得文件的真实路径,构建文件对象关联真实路径
		File file=new File(a.getDcont());
		//3.检测文件是否存在，存在则下载　
		if(!file.exists())
		throw new RuntimeException("文件已经不存在");
		return file;
	}

	@Override
	public int insertProject(Attachement entity) {
		int ret = attachementDao.insertObject(entity);
		return ret;
	}
}
