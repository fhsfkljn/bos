package com.chao.bos.service.impl;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chao.bos.dao.IRoleDao;
import com.chao.bos.dao.IUserDao;
import com.chao.bos.domain.Role;
import com.chao.bos.domain.User;
import com.chao.bos.service.IUserService;
import com.chao.bos.utils.MD5Utils;
import com.chao.bos.utils.PageBean;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	//注入dao
	@Autowired
	private IUserDao userDao;
	@Resource
	private IdentityService identityService;
	@Autowired
	private IRoleDao roleDao;
	
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();//明文
		password = MD5Utils.md5(password);//md5加密
		return userDao.findByUsernameAndPassword(username,password);
	}

	public void editPassword(String password, String id) {
		userDao.executeUpdate("editPassword", password,id);
	}
	
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}
	
	public void save(User user, String[] roleIds) {
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		user.setPassword(password);
		userDao.save(user);//持久对象 
		
		org.activiti.engine.identity.User actUser = new UserEntity(user.getId());
		identityService.saveUser(actUser);
		
		for (String roleId : roleIds) {
			Role role = roleDao.findById(roleId);
			//用户关联角色
			user.getRoles().add(role);
			identityService.createMembership(actUser.getId(), role.getName());
		}
	}

}
