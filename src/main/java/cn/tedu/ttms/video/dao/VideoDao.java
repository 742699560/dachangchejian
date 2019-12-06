package cn.tedu.ttms.video.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.video.entity.VideoEntity;

public interface VideoDao extends BaseDao<VideoEntity>{
	public List<VideoEntity> findPageObjects(@Param("entity")VideoEntity entity,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")VideoEntity entity);
	public int findNumber(int vid);
	
}
