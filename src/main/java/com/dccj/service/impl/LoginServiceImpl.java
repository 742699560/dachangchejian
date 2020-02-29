package com.dccj.service.impl;


import javax.annotation.Resource;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dccj.service.LoginService;
import com.dccj.dao.UserDao;
import com.dccj.entity.User;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private UserDao userDao;
	

	@Override
	public User isExist(String username) {
		if(username==null){
			throw new NullPointerException("登录，用户名不能为空！");
		}
		int i = userDao.isExist(username);
		if(i!=1){
			throw new UnknownAccountException("该用户不存在！");
		}
		//根据用户名找到用户信息
		User user = userDao.findObjectByName(username);
		return user;
	}

	


}
