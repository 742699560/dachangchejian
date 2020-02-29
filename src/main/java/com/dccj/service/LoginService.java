package com.dccj.service;


import com.dccj.entity.User;

public interface LoginService {

	/**
	 * 根据用户名判断用户是否存在
	 * @param username
	 * @return 
	 */
	User isExist(String username);

	

	/**
	 * 校验密码
	 * @param username
	 * @param userpwd
	 * @return 
	 */
//	User confirmPwd(String username, String userpwd);

}
