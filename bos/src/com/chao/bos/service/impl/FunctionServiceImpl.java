package com.chao.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chao.bos.dao.IFunctionDao;
import com.chao.bos.domain.Function;
import com.chao.bos.domain.User;
import com.chao.bos.service.IFunctionService;
import com.chao.bos.utils.BOSContext;
import com.chao.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{
	@Autowired
	private IFunctionDao functionDao;

	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	public List<Function> findAll() {
		return functionDao.findAll();
	}

	public void save(Function model) {
		Function function = model.getFunction();
		if (function != null && function.getId().equals("")) {
			model.setFunction(null);
		}
		functionDao.save(model);
	}

	/**
	 * ��ѯ�˵�
	 */
	public List<Function> findMenu() {
		User user = BOSContext.getLoginUser();
		List<Function> list = null;
		if (user.getUsername().equals("admin")) {
			//��ѯ���в˵�
			list = functionDao.findAllMenu();
		}else{
			//��ͨ�û�����ѯ��Ӧ�Ĳ˵�
			list = functionDao.findMenuByUserid(user.getId());
		}
		return list;
	}
}
