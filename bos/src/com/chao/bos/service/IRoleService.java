package com.chao.bos.service;

import java.util.List;

import com.chao.bos.domain.Role;
import com.chao.bos.utils.PageBean;

public interface IRoleService {
	
	public void save(Role model, String ids);

	public void pageQuery(PageBean pageBean);

	public List<Role> findAll();
}
