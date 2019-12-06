package cn.tedu.ttms.homeMessage.service;

import java.util.Map;

import cn.tedu.ttms.homeMessage.entity.HomeMessageEntity;

public interface HomeMessageService {
		
	Map<String,Object>  findAboutprove(Integer currentPage);
	Map<String,Object>  findDueapprove(Integer currentPage);
	HomeMessageEntity  findPageNumber ();
}
