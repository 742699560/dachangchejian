package com.dccj.controller;

import javax.annotation.Resource;

import com.dccj.exception.AppException;
import com.dccj.uitl.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dccj.service.LoginService;

@Controller
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping("/toLogin")
	public String login(){
		return "login";
	}
	
	/**
	 * 用户登录，校验用户信息
	 */
	@RequestMapping("/confirmUser")
	@ResponseBody
	public JsonResult confirmUser(String username, String userpwd){
		
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
           try{
        	 //登录认证 - 调用userRealm
               currentUser.login(token);
           }catch (IncorrectCredentialsException ice) {
               throw new AppException("密码错误！");
           } 
           catch(AuthenticationException ae){
        	   throw new AppException(ae.getMessage());
           }
		}
		return new JsonResult();
	}
	
	
	
}
