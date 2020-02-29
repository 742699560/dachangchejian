package com.dccj.dao;

import com.dccj.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao extends BaseDao<UserRole> {

	/**
	 * 判断用户角色表中是否存在用户占用此角色
	 * @param roleId
	 */
	int isExist(@Param("roleId")Integer roleId);

}
