package com.dccj.dao;

import java.util.List;
import java.util.Map;

import com.dccj.entity.User;

public interface UserDao extends BaseDao<User> {

	int isExist(String username);

	/**
	 * 查询用户得权限
	 * @param userId
	 */
	List<String> findPermission(Integer userId);

	/**
	 * 查询用户可见的菜单
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> menuList(Integer id);

	

}
