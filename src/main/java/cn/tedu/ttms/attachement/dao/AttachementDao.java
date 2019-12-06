package cn.tedu.ttms.attachement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;

public interface AttachementDao 
   extends BaseDao<Attachement>{

	public List<Attachement> findPageObjects(@Param("entity")Attachement entity,@Param("pageObj")PageObject pageObj);
	public int getRowCount(@Param("entity")Attachement entity);
	Attachement findAttachementById(Integer id);
	
}
