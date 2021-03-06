package com.chao.bos.service;

import com.chao.bos.domain.User;
import com.chao.bos.utils.PageBean;

public interface IUserService {

	public User login(User model);

	public void editPassword(String password, String id);

	public void pageQuery(PageBean pageBean);

	public void save(User model, String[] roleIds);
	
}
