package com.chao.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chao.bos.domain.Workordermanage;
import com.chao.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 工作单管理
 * @author zhaoqx
 *
 */
@Controller
@Scope
public class WorkordermanageAction extends BaseAction<Workordermanage>{
	
	public String add() throws IOException{
		String flag = "1";
		try{
			workordermanageService.save(model);
		}catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
	/**
	 * 查询start为0的工作单
	 */
	public String list(){
		List<Workordermanage> list = workordermanageService.findListNotStart();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	/**
	 * 启动物流配送流程
	 */
	public String start(){
		return "toList";
	}
}
