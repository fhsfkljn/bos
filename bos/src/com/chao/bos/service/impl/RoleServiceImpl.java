package com.chao.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chao.bos.dao.IRoleDao;
import com.chao.bos.domain.Function;
import com.chao.bos.domain.Role;
import com.chao.bos.service.IRoleService;
import com.chao.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IdentityService identityService;
	
	public void save(Role role, String ids) {//1,2,3,4
		roleDao.save(role);//持久对象
		//使用角色的名称作为组的id
		Group group = new GroupEntity(role.getName());
		identityService.saveGroup(group);
		
		String[] functionIds = ids.split(",");
		for (String fid : functionIds) {
			Function function = new Function(fid);//托管,离线对象
			//角色关联权限
			role.getFunctions().add(function);
		}
	}
	
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}
	
	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
