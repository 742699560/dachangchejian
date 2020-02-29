package com.dccj.dao;
import java.util.List;
import java.util.Map;

import com.dccj.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	List<Map<String, Object>> findRoleList();

	

}
