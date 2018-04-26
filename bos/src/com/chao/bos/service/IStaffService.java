package com.chao.bos.service;

import com.chao.bos.domain.Staff;
import com.chao.bos.utils.PageBean;

public interface IStaffService {
	
	public void save(Staff model);

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff findById(String id);

	public void update(Staff staff);
}
