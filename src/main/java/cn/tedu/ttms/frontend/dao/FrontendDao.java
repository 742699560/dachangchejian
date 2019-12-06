package cn.tedu.ttms.frontend.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.frontend.entity.FrontendEntity;

public interface FrontendDao extends BaseDao<FrontendEntity> {
   public List<Map<String,Object>> getlxsData();
   public List<Map<String,Object>> getCompanyData();
   public int getWarnNumByYM(@Param("date")Date date,@Param("type")Integer type);
}
