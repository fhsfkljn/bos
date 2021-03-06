package com.chao.bos.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessInstanceAction extends ActionSupport {

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;

	/**
	 * 查询流程实例列表数据
	 */
	public String list() {
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		query.orderByProcessDefinitionId().desc();
		List<ProcessInstance> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}

	private String id; // 接受流程实例id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 根据流程实例id查询相应的流程变量数据
	 * 
	 * @throws IOException
	 */
	public String findData() throws IOException {
		Map<String, Object> variables = runtimeService.getVariables(id);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(variables.toString());
		return NONE;
	}

	/**
	 * 根据流程实例id查询坐标、部署id、图片名称
	 */
	public String showPng() {
		// 1、根据流程实例id查询流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id)
				.singleResult();
		// 2、根据流程实例对象查询流程定义id
		String processDefinitionId = processInstance.getProcessDefinitionId();
		// 3、根据流程定义id查询流程定义对象
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		// 4、根据流程定义对象查询部署id
		deploymentId = processDefinition.getDeploymentId();
		imageName = processDefinition.getDiagramResourceName();

		// 查询坐标
		// 1、获得当前流程实例执行到哪个节点
		String activityId = processInstance.getActivityId();// usertask1
		// 2、加载bpmn（xml）文件，获得一个流程定义对象
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);// 查询act_ge_bytearray
		// 3、根据activitiId获取含有坐标信息的对象
		ActivityImpl findActivity = pd.findActivity(activityId);
		int x = findActivity.getX();
		int y = findActivity.getY();
		int width = findActivity.getWidth();
		int height = findActivity.getHeight();

		ActionContext.getContext().getValueStack().set("x", x);
		ActionContext.getContext().getValueStack().set("y", y);
		ActionContext.getContext().getValueStack().set("width", width);
		ActionContext.getContext().getValueStack().set("height", height);

		return "showPng";
	}

	private String deploymentId;

	private String imageName;

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * 获取png输入流
	 */
	public String viewImage() {

		// 获取png图片对应的输入流
		InputStream pngStream = repositoryService.getResourceAsStream(deploymentId, imageName);
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "viewImage";
	}
}
