package com.chao.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chao.bos.domain.Staff;
import com.chao.bos.service.IStaffService;
import com.chao.bos.web.action.base.BaseAction;

/**
 * ȡ��Ա����
 * 
 * @author 
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

	// ע��Service
	@Autowired
	private IStaffService staffService;
	
	/**
	 * ���ȡ��Ա
	 */
	public String add(){
		staffService.save(model);
		return "list";
	}
}
