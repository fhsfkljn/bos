package com.chao.bos.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chao.bos.domain.User;
import com.chao.bos.service.IUserService;
import com.chao.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private IUserService userService;
	
	// ͨ����������������֤��
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	public String login(){
		//���ɵ���֤��
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		//�ж��û��������֤���Ƿ���ȷ
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			//��֤����ȷ
			User user = userService.login(model);
			if(user != null){
				//��¼�ɹ�,��User����session����ת��ϵͳ��ҳ
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}else{
				//��¼ʧ�ܣ����ô�����ʾ��Ϣ����ת����¼ҳ��
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		}else {
			//��֤�����,���ô�����ʾ��Ϣ����ת����¼ҳ��
			this.addActionError(this.getText("validateCodeError"));
			return "login";
		}
	}
}
