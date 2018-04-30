package com.chao.bos.service.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chao.bos.dao.IWorkordermanageDao;
import com.chao.bos.domain.Workordermanage;
import com.chao.bos.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService{
	@Autowired
	private IWorkordermanageDao workordermanageDao;
	
	public void save(Workordermanage model) {
		model.setUpdatetime(new Date());
		workordermanageDao.save(model);
	}
}
