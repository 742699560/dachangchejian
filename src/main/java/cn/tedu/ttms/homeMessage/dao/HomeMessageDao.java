package cn.tedu.ttms.homeMessage.dao;

import java.util.List;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.homeMessage.entity.HomeMessageEntity;

public interface HomeMessageDao extends BaseDao<HomeMessageEntity>{

	
	List<HomeMessageEntity> findDueapprove();
	List<HomeMessageEntity> findAboutprove();
	public int  findDueapproveNumber ();
	public int  findAboutproveNumber();
	public int  findComnumner();
	public int  findTalnumner();
}
