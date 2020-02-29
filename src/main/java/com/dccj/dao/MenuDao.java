package com.dccj.dao;

import java.util.List;
import java.util.Map;

import com.dccj.entity.Menu;


public interface MenuDao extends BaseDao<Menu> {

	List<Map<String, Object>> findTreeData();

	int hasChild(Integer menuId);

}
