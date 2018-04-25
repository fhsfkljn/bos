package com.chao.bos.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.chao.bos.dao.IUserDao;
import com.chao.bos.dao.base.impl.BaseDaoImpl;
import com.chao.bos.domain.User;

@Repository
//@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
	/**
	 * �����û����������ѯ�û�
	 */
	public User findByUsernameAndPassword(String username, String password) {
		System.out.println(username);
		System.out.println(password);
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username,password);
		System.out.println(list);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
