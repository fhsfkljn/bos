package com.chao.bos.service;

import com.chao.bos.domain.User;

public interface IUserService {

	public User login(User model);

	public void editPassword(String password, String id);
	
}
