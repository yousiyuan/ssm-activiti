package com.activiti.demo.flow.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.demo.commons.SequenceflowTypeEnum;
import com.activiti.demo.flow.service.FlowService;

@Service
public class FlowServiceImpl implements FlowService {

	/**
	 * 管理流程定义
	 */
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 执行管理，包括启动、推进、删除流程实例等操作
	 */
	@Autowired

	private RuntimeService runtimeService;

	/**
	 * 任务管理
	 */
	@Autowired
	private TaskService taskService;

	/**
	 * 历史管理(执行完的数据的管理)
	 */
	@Autowired
	private HistoryService historyService;

	@Override
	public void initializeActivitiProcessEngine(String configPath) {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource(configPath);
		processEngineConfiguration.buildProcessEngine();
	}

	@Override
	public Deployment deployProcess(String resourcePath, String name, String category) {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);
		ZipInputStream zipInputStream = new ZipInputStream(inStream);
		deploymentBuilder.addZipInputStream(zipInputStream).name(name).category(category);
		Deployment deploy = deploymentBuilder.deploy();
		return deploy;
	}

	@Override
	public void undeployProcess(String deploymentId, Boolean cascade) {
		repositoryService.deleteDeployment(deploymentId, cascade);
	}

	@Override
	public ProcessDefinition queryProcessDefinitionById(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	@Override
	public List<ProcessDefinition> queryProcessDefinitionByKey(String processDefinitionKey) {
		List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).list();
		return processDefinitionList;
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		return processInstance;
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> map) {
		ProcessInstance processInstance = null;
		if (map == null)
			processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		else
			processInstance = runtimeService.startProcessInstanceById(processDefinitionId, map);
		return processInstance;
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey,
			Map<String, Object> map) {
		ProcessInstance processInstance = null;
		if (map == null)
			processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey);
		else
			processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, map);
		return processInstance;
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		return processInstance;
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> map) {
		ProcessInstance processInstance = null;
		if (map == null)
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		else
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
		return processInstance;
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
			Map<String, Object> map) {
		ProcessInstance processInstance = null;
		if (map == null)
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
		else
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, map);
		return processInstance;
	}

	@Override
	public ProcessInstance queryProcessInstanceById(String processDefinitionId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processInstance;
	}

	@Override
	public List<ProcessInstance> queryProcessInstanceByKey(String processDefinitionKey) {
		List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey(processDefinitionKey).orderByProcessDefinitionId().asc().list();
		return processInstanceList;
	}

	@Override
	public List<Task> queryTaskByAssignee(String assignee) {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc()
				.list();
		return taskList;
	}

	@Override
	public List<Task> queryTasks(String processInstanceId) {
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime()
				.desc().list();
		return taskList;
	}

	@Override
	public List<Task> queryGroupTasks(String candidateUser) {
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(candidateUser).orderByTaskCreateTime().desc()
				.list();
		return list;
	}

	@Override
	public Task queryGroupTask(String processInstanceId, String candidateUser) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateUser(candidateUser)
				.singleResult();
		return task;
	}

	@Override
	public List<IdentityLink> queryGroupCandidateUsers(String taskId) {
		List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(taskId);
		return identityLinkList;
	}

	@Override
	public void claim(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}

	@Override
	public void unclaim(String taskId) {
		// 归还任务，没错，就是将某某字段的值重置为NULL
		taskService.claim(taskId, null);
	}

	@Override
	public List<Execution> queryExecutionByProcDefId(String processDefinitionId) {
		List<Execution> executionList = runtimeService.createExecutionQuery().processDefinitionId(processDefinitionId)
				.list();
		return executionList;
	}

	@Override
	public List<Execution> queryExecutionByProcInstId(String processInstanceId) {
		List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
				.list();
		return executionList;
	}

	@Override
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}

	@Override
	public void completeTask(String taskId, Map<String, Object> map) {
		taskService.complete(taskId, map);
	}

	@Override
	public String queryProcessState(String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (processInstance == null) {
			return "流程已结束";
		} else {
			List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
			String taskDesc = null;
			for (Task task : list) {
				taskDesc += task.getTaskDefinitionKey() + "(" + task.getName() + ");";
			}
			return "流程任务:" + taskDesc;
		}
	}

	@Override
	public Map<String, Object> getVariablesFromTask(String taskId) {
		Map<String, Object> variables = taskService.getVariables(taskId);
		return variables;
	}

	@Override
	public Map<String, Object> getVariablesFromExecution(String executionId) {
		Map<String, Object> variables = runtimeService.getVariables(executionId);
		return variables;
	}

	@Override
	public void setVariablesByTask(String taskId, Map<String, Object> map) {
		taskService.setVariables(taskId, map);
	}

	@Override
	public void setVariablesByExecution(String executionId, Map<String, Object> map) {
		runtimeService.setVariables(executionId, map);
	}

	@Override
	public void setCustomVariableByTask(String taskId, String variableName, Object value) {
		taskService.setVariable(taskId, variableName, value);
	}

	@Override
	public void setCustomVariableByExecution(String executionId, String variableName, Object value) {
		runtimeService.setVariable(executionId, variableName, value);
	}

	@Override
	public Object getCustomVariableFromTask(String taskId, String variableName) {
		Object variable = taskService.getVariable(taskId, variableName);
		return variable;
	}

	@Override
	public Object getCustomVariableFromExecution(String executionId, String variableName) {
		Object variable = runtimeService.getVariable(executionId, variableName);
		return variable;
	}

	@Override
	public List<HistoricProcessInstance> queryHistoricProcessInstanceByProcDefId(String processDefinitionId) {
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionId(processDefinitionId).orderByProcessInstanceEndTime().desc().list();
		return list;
	}

	@Override
	public HistoricProcessInstance queryHistoricProcessInstanceByProcInstId(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		return historicProcessInstance;
	}

	@Override
	public List<HistoricActivityInstance> queryHistoricActivityInstance(String processInstanceId) {
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime()
				.orderByHistoricActivityInstanceEndTime()
				// .activityType("userTask")//过滤“Start event”、“End event”节点
				.asc().list();
		return list;
	}

	@Override
	public List<HistoricTaskInstance> queryHistoricTaskInstance(String processInstanceId) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).asc().list();
		return list;
	}

	@Override
	public List<HistoricTaskInstance> queryHistoricTaskInstanceByAssignee(String assignee) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).asc()
				.list();
		return list;
	}

	@Override
	public List<HistoricVariableInstance> queryHistoricVariableInstance(String processInstanceId) {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstanceId).list();
		return list;
	}

	@Override
	public List<HistoricIdentityLink> queryHistoricIdentityLinksForTask(String taskId) {
		List<HistoricIdentityLink> list = historyService.getHistoricIdentityLinksForTask(taskId);
		return list;
	}

	/**
	 * 查询当前User Task的incoming sequence flow
	 * 
	 * @param proDefId
	 *            流程定义ID
	 * @param proInsId
	 *            流程实例ID
	 */
	@Override
	public String queryIncomingSequenceFlow(String proDefId, String proInsId) {
		/*
		 * 步骤： 第一步：获得当前节点的所有来路（其中一条是来路）
		 * 第二步：拿到上一步的节点（历史节点），找到它的所有出路（其中有一条出路是当前节点的来路）
		 * 第三步：找到以上两步中的重复的连线就是当前节点的来路
		 */

		// 当前User Task定义的Key
		String nowActivitiId = queryCurrentActivitiId(proInsId);
		// 获得当前节点的所有incoming sequence flow
		List<String> userTaskList = querySequenceFlow(SequenceflowTypeEnum.FLOW_IN, proDefId, nowActivitiId);

		if (userTaskList.size() == 1) {
			return userTaskList.get(0);
		}

		// 上一个User Task定义的Key
		String preActivitiId = queryPreviousActivitiId(proInsId);
		// 获得当前节点的上一个节点所有的outgoing sequence flow
		List<String> lastUserTaskSeqFlowList = querySequenceFlow(SequenceflowTypeEnum.FLOW_OUT, proDefId,
				preActivitiId);

		String sequenceflow = userTaskList.stream().filter(item -> lastUserTaskSeqFlowList.contains(item))
				.collect(Collectors.toList()).get(0);
		return sequenceflow;
	}

	/**
	 * 查询当前User Task的上一个User Task定义的Key
	 * 
	 * @param processInstanceId
	 *            流程实例的ID
	 */
	@Override
	public String queryPreviousActivitiId(String processInstanceId) {
		// 获得当前的流程实例所经历的历史任务
		List<HistoricActivityInstance> historicActivityInstanceList = historyService
				.createHistoricActivityInstanceQuery()// 创建当前实例历史任务的查询对象
				.processInstanceId(processInstanceId)// 根据当前实例的id来查询
				// .activityType("user_task")
				.orderByHistoricActivityInstanceStartTime()// 根据当前实例历史获得开始时间倒序来排序
				.finished()// 已经结束的活动节点
				.desc().list();

		// 获取前一个活动节点
		HistoricActivityInstance historicActivityInstance = historicActivityInstanceList.get(0);
		// 获取活动ID
		String activitiyId = historicActivityInstance.getActivityId();
		return activitiyId;
	}

	/**
	 * 查询当前活动的 User Task定义的Key
	 * 
	 * @param processInstanceId
	 *            流程实例的ID
	 */
	@Override
	public String queryCurrentActivitiId(String processInstanceId) {
		// 查询当前流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).list().stream().findFirst().get();
		// 获得正在执行的活动
		String activityId = processInstance.getActivityId();
		return activityId;
	}

	/**
	 * 获得某个User Task所有的incoming sequence flow 或 outgoing sequence flow
	 * 
	 * @param flowType
	 *            incoming or outgoing
	 * @param processDefinitionId
	 *            流程定义的ID
	 * @param activityId
	 *            User Task的Key
	 */
	@Override
	public List<String> querySequenceFlow(SequenceflowTypeEnum flowType, String processDefinitionId,
			String activityId) {
		List<String> flows = new ArrayList<String>();
		// 查询流程定义
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 获得正在执行的活动（包括流程图上所有的节点）
		ActivityImpl activity = processDefinitionEntity.findActivity(activityId);

		if (flowType.equals(SequenceflowTypeEnum.FLOW_IN)) {
			// 获得所有进来的Sequence flow（线的对象）
			List<PvmTransition> incomingPvmTransitionList = activity.getIncomingTransitions();
			incomingPvmTransitionList.forEach(items -> {
				flows.add(items.getId() + "：" + (String) items.getProperty("name"));
			});
		} else if (flowType.equals(SequenceflowTypeEnum.FLOW_OUT)) {
			// 获得所有出去的Sequence flow（线的对象）
			List<PvmTransition> outgoingPvmTransitionList = activity.getOutgoingTransitions();
			outgoingPvmTransitionList.forEach(items -> {
				flows.add(items.getId() + "：" + (String) items.getProperty("name"));
			});
		}
		return flows;
	}

	/**
	 * 让执行对象向下执行，主要用于Receive task节点类型，只有执行对象，没有任务
	 * 
	 * @param executionId
	 *            执行对象Id
	 */
	@Override
	public void signal(String executionId) {
		runtimeService.signal(executionId);
	}

	/**
	 * 让执行对象向下执行，主要用于Receive task节点类型，只有执行对象，没有任务
	 * 
	 * @param executionId
	 *            执行对象Id
	 * @param processVariables
	 *            流程变量
	 */
	@Override
	public void signal(String executionId, Map<String, Object> processVariables) {
		runtimeService.signal(executionId, processVariables);
	}

}
