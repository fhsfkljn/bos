package com.chao.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chao.bos.domain.Function;
import com.chao.bos.web.action.base.BaseAction;

/**
 * 权限管理
 * @author chao
 *
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	
	/**
	 * 分页查询方法
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		String page = model.getPage();
		pageBean.setCurrentPage((Integer.parseInt(page)));
		functionService.pageQuery(pageBean);
		String[] excludes = new String[]{"function","functions","roles","currentPage","detachedCriteria","pageSize"};
		this.writePageBean2Json(pageBean, excludes );
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Function> list = functionService.findAll();
		String[] excludes = new String[]{"function","functions","roles"};
		this.writeList2Json(list, excludes );
		return NONE;
	}
	
	/**
	 * 添加权限
	 */
	public String add(){
		functionService.save(model);
		return "list";
	}
	
	/**
	 * 根据登录人查询对应的菜单数据（从权限表中查询）
	 * @throws IOException 
	 */
	public String findMenu() throws IOException{
		List<Function> list = functionService.findMenu();
		String[] excludes = new String[]{"functions","function","roles"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
}
