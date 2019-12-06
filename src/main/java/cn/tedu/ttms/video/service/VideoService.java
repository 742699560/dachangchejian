package cn.tedu.ttms.video.service;

import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.video.entity.VideoEntity;

public interface VideoService {
	Map<String,Object> findPageObjects(VideoEntity entity,PageObject pageObj);
	Map<String, Object> findComopanyById(Integer userId);
	void  saveObject(VideoEntity entity);
	void updateObject(VideoEntity entity);
	void deletObject(Integer id);
	int findNumber(Integer id);
}
