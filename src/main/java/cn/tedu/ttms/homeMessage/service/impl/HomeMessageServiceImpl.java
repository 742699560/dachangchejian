package cn.tedu.ttms.homeMessage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.web.Page;
import cn.tedu.ttms.homeMessage.dao.HomeMessageDao;
import cn.tedu.ttms.homeMessage.entity.HomeMessageEntity;
import cn.tedu.ttms.homeMessage.service.HomeMessageService;
@Service
@Transactional
public class HomeMessageServiceImpl implements HomeMessageService {
	@Resource
	private HomeMessageDao homeMessageDao;
	
	@Override
	public Map<String,Object>  findAboutprove(Integer currentPage) {
		Page pageObj=new Page();
		pageObj.setPageCurrent(currentPage);
		List<HomeMessageEntity> list = homeMessageDao.findAboutprove();
		  if (pageObj.getPageCurrent() == null){
		    	pageObj.setPageCurrent(1);
		    } else {
		    	pageObj.setPageCurrent(pageObj.getPageCurrent());
		    }
		    pageObj.setPageSize(5);
		    pageObj.setStartIndex((pageObj.getPageCurrent() - 1) * pageObj.getPageSize());
		    int count = list.size();
		    pageObj.setPageCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		    pageObj.setDataList(list.subList(pageObj.getStartIndex(),count-pageObj.getStartIndex()>pageObj.getPageSize()?pageObj.getStartIndex()+pageObj.getPageSize():count));
		    pageObj.setRowCount(list.size());
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("pageObj", pageObj);
			return map;
	}
	@Override
	public Map<String,Object>  findDueapprove(Integer currentPage) {
		Page pageObj=new Page();
		pageObj.setPageCurrent(currentPage);
		List<HomeMessageEntity> list = homeMessageDao.findDueapprove();
		  if (pageObj.getPageCurrent() == null){
		    	pageObj.setPageCurrent(1);
		    } else {
		    	pageObj.setPageCurrent(pageObj.getPageCurrent());
		    }
		    pageObj.setPageSize(5);
		    pageObj.setStartIndex((pageObj.getPageCurrent() - 1) * pageObj.getPageSize());
		    int count = list.size();
		    pageObj.setPageCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		    pageObj.setDataList(list.subList(pageObj.getStartIndex(),count-pageObj.getStartIndex()>pageObj.getPageSize()?pageObj.getStartIndex()+pageObj.getPageSize():count));
		    pageObj.setRowCount(list.size());
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("pageObj", pageObj);
			return map;
	}
	
	@Override
	public HomeMessageEntity findPageNumber() {
		HomeMessageEntity homeMessageEntity = new HomeMessageEntity();
		homeMessageEntity.setAboutprove(homeMessageDao.findAboutproveNumber());
		homeMessageEntity.setComnumner(homeMessageDao.findComnumner());
		homeMessageEntity.setDueapprove(homeMessageDao.findDueapproveNumber());
		homeMessageEntity.setTalnumner(homeMessageDao.findTalnumner());
		return homeMessageEntity;
	}
}
