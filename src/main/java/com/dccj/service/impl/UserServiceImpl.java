package com.dccj.service.impl;

import com.dccj.uitl.PageObject;
import com.dccj.uitl.SpringListUtil;
import com.dccj.dao.RoleDao;
import com.dccj.dao.UserDao;
import com.dccj.dao.UserRoleDao;
import com.dccj.entity.User;
import com.dccj.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserRoleDao userRoleDao;

	public Map<String, Object> findObjects(User user, PageObject pageObject) {
		List<Map<String, Object>> list =  userDao.findObjects(user, pageObject);
		pageObject.setRowCount(userDao.getRowCounts(user));	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("page",pageObject.getPageCurrent());
		map.put("total",pageObject.getPageCount());
		map.put("Result", true);
		return map;
	}

	public List<Map<String, Object>> findRoleList() {
		List<Map<String, Object>> list = roleDao.findRoleList();
		if(list==null || list.size()==0){
			throw new RuntimeException("获取角色列表失败！");
		}
		return list;
	}

	/**
	 * 保存用户信息，先保存用户，再保存用户角色关系
	 */
	public void saveUser(User user,String roleIdList) {
		if(user==null){
			throw new NullPointerException("保存用户信息，用户对象不能为空！");
		}
		String saltStr = UUID.randomUUID().toString();
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		String pwd = new SimpleHash("MD5",user.getPassword(),salt).toString();
//		String pwd = HashUtil.hashMD5Base64(salt, user.getPassword());
		user.setPassword(pwd);
		user.setSalt(saltStr);
		//保存用户信息
		int i = userDao.save(user);
		if(i!=1){
			throw new RuntimeException("保存用户信息失败！");
		}
		//保存用户角色信息
		List<Integer> listInt = null;
		listInt = SpringListUtil.parseIntegerList(roleIdList, listInt);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		map.put("roleIdList",listInt);
		
		int counts = userRoleDao.save(map);
		if(counts!=listInt.size()){
			throw new RuntimeException("保存用户角色失败！");
		}
	}
	

	/**
	 * 根据id查询用户信息
	 * @return 
	 */

	public Map<String, Object> findUserById(Integer userId) {
		if(userId==null){
			throw new NullPointerException("查询用户信息，用户id不能为空！");
		}
		Map<String, Object> map = userDao.findObjectById(userId);
		if(map==null || map.size()==0){
			throw new RuntimeException("查询用户信息失败！");
		}
		List<Integer> roleIdList = userRoleDao.findRelatedIdListById(userId);
		if(roleIdList==null||roleIdList.size()==0){
			throw new RuntimeException("修改用户信息，查询用户角色信息失败！");
		}
		map.put("roleIdList",roleIdList);
		return map;
	}


	public void updateUser(User user,String roleIdList) {
		if(user==null){
			throw new NullPointerException("保存用户信息，用户对象不能为空！");
		}
		if(!StringUtils.isEmpty(user.getPassword())) {
			String saltStr = UUID.randomUUID().toString();   //生成盐值
			ByteSource salt = ByteSource.Util.bytes(saltStr);
			String pwd = new SimpleHash("MD5", user.getPassword(), salt, 1).toString();
			user.setPassword(pwd);
			user.setSalt(saltStr);
		}
		//更新用户信息
		int i = userDao.updateObject(user);
		if(i!=1){
			throw new RuntimeException("修改更新用户信息失败！");
		}
		List<Integer> listInt = null;
		listInt = SpringListUtil.parseIntegerList(roleIdList, listInt);
		
		int counts = userRoleDao.deletObject(user.getId());
		if(counts<1){
			throw new RuntimeException("修改更新用户角色信息失败！");
		}
		//保存用户角色关系
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		map.put("roleIdList", listInt);
		
		int rows = userRoleDao.save(map);
		if(rows!=listInt.size()){
			throw new RuntimeException("修改更新用户角色失败！");
		}
	}


	public void changeState(Integer userId, Integer valid) {
		if(userId==null){
			throw new RuntimeException("修改用户状态，用户id不能为空！");
		}
		if(valid==null){
			throw new RuntimeException("修改用户状态，valid值不能为空！");
		}
		int i = userDao.changeState(userId,valid);
		if(i!=1){
			throw new RuntimeException("切换用户启用禁用状态失败！");
		}
	}

	/**
	 * 查询用户得所有权限
	 */

	public List<String> findPermission(Integer userId) {
		if(userId==null){
			throw new NullPointerException("查询用户权限用户id不能为空！");
		}
		return userDao.findPermission(userId);
	}
	

	public List<Map<String, Object>> menuList() {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		return userDao.menuList(user.getId());
	}

	public static void main(String[] args) {
		String saltStr = UUID.randomUUID().toString();
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		String pwd = new SimpleHash("MD5","123456",salt).toString();
		System.out.println(pwd + "--------"+saltStr);
	}
}
