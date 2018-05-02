package com.chao.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chao.bos.domain.Role;
import com.chao.bos.web.action.base.BaseAction;

/**
 * ��ɫ����
 * @author chao
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private String ids;//����Ȩ��id
	
	public String getIds() {
		return ids;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * ��ӽ�ɫ
	 */
	public String add(){
		roleService.save(model,ids);
		return "list";
	}
	
	/**
	 * ��ҳ��ѯ����
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		roleService.pageQuery(pageBean);
		//��PageBean����תΪjson����
		this.writePageBean2Json(pageBean, new String[]{"functions","users","currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	/**
	 * ��ѯ���еĽ�ɫ���ݣ�����json
	 * @return
	 * @throws IOException
	 */
	public String listajax() throws IOException{
		List<Role> list = roleService.findAll();
		String[] excludes = new String[]{"functions","users"};
		this.writeList2Json(list, excludes );
		return NONE;
	}
	
	
}
