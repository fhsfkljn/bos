package com.chao.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chao.bos.dao.IUserDao;
import com.chao.bos.domain.User;
import com.chao.bos.service.IUserService;
import com.chao.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	//注入dao
	@Autowired
	private IUserDao userDao;
	
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();//明文
		password = MD5Utils.md5(password);//md5加密
		return userDao.findByUsernameAndPassword(username,password);
	}

	public void editPassword(String password, String id) {
		userDao.executeUpdate("editPassword", password,id);
	}

}
