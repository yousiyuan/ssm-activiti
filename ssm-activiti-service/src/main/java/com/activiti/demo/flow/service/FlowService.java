package com.activiti.demo.flow.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import com.activiti.demo.commons.SequenceflowTypeEnum;

public interface FlowService {

	/**
	 * 初始化Activiti流程引擎
	 * 
	 * @param configPath
	 *            配置文件(activiti.cfg.xml)的classpath路径
	 */
	public abstract void initializeActivitiProcessEngine(String configPath);

	/**
	 * 部署流程
	 * 
	 * @param resourcePath
	 *            流程图文件以及流程图(*.png)的压缩包文件路径(*.zip)
	 * @param name
	 *            流程图名称
	 * @param category
	 *            流程图相关业务类别
	 */
	public abstract Deployment deployProcess(String resourcePath, String name, String category);

	/**
	 * 删除部署的流程
	 * 
	 * @param deploymentId
	 *            流程部署标识
	 * @param cascade
	 *            值为true，级联删除，即使流程已经启动，正在运行，也可以删除；值为false，非级联删除，若流程已启动，会报错
	 */
	public abstract void undeployProcess(String deploymentId, Boolean cascade);

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return 流程定义对象
	 */
	public abstract ProcessDefinition queryProcessDefinitionById(String processDefinitionId);

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionKey
	 *            流程定义Key
	 * @return 流程定义对象列表
	 */
	public abstract List<ProcessDefinition> queryProcessDefinitionByKey(String processDefinitionKey);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceById(String processDefinitionId);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @param map
	 *            流程变量
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> map);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @param businessKey
	 *            业务Key
	 * @param map
	 *            流程变量
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey,
			Map<String, Object> map);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionKey
	 *            流程定义Key。根据流程定义Key启动流程，如果定义相同Key的流程有多个，将会优先启动最高版本对应的流程（ACT_RE_PROCDEF表的字段VERSION_
	 *            值最大的那条记录），如果想启动其它版本，可以根据流程定义的ID启动流程
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceByKey(String processDefinitionKey);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionKey
	 *            流程定义Key。根据流程定义Key启动流程，如果定义相同Key的流程有多个，将会优先启动最高版本对应的流程（ACT_RE_PROCDEF表的字段VERSION_
	 *            值最大的那条记录），如果想启动其它版本，可以根据流程定义的ID启动流程
	 * @param map
	 *            流程变量
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> map);

	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionKey
	 *            流程定义Key。根据流程定义Key启动流程，如果定义相同Key的流程有多个，将会优先启动最高版本对应的流程（ACT_RE_PROCDEF表的字段VERSION_
	 *            值最大的那条记录），如果想启动其它版本，可以根据流程定义的ID启动流程
	 * @param businessKey
	 *            业务Key
	 * @param map
	 *            流程变量
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
			Map<String, Object> map);

	/**
	 * 查询流程实例
	 * 
	 * @param processDefinitionId
	 *            流程定义Id
	 * @return 流程实例对象
	 */
	public abstract ProcessInstance queryProcessInstanceById(String processDefinitionId);

	/**
	 * 查询流程实例
	 * 
	 * @param processDefinitionKey
	 *            流程定义Key
	 * @return 流程实例对象列表
	 */
	public abstract List<ProcessInstance> queryProcessInstanceByKey(String processDefinitionKey);

	/**
	 * 查询任务
	 * 
	 * @param assignee
	 *            办理人
	 * @return 任务列表
	 */
	public abstract List<Task> queryTaskByAssignee(String assignee);

	/**
	 * 查询一支流程的待办任务
	 * 
	 * @param processInstanceId
	 *            流程实例Id
	 * @return 任务列表
	 */
	public abstract List<Task> queryTasks(String processInstanceId);

	/**
	 * 查询任何一个组成员可接管的任务
	 * 
	 * @param candidateUser
	 *            组成员用户标识
	 * @return 任务列表
	 */
	public abstract List<Task> queryGroupTasks(String candidateUser);

	/**
	 * 查询一个组任务的所有接管人
	 * 
	 * @param taskId
	 *            任务Id
	 * @return 接管对象列表
	 */
	public abstract List<IdentityLink> queryGroupCandidateUsers(String taskId);

	/**
	 * 查询某支流程中任何一个组成员可接管的任务
	 * 
	 * @param processInstanceId
	 *            流程实例Id
	 * @param candidateUser
	 *            组成员用户标识
	 * @return 任务列表
	 */
	public abstract Task queryGroupTask(String processInstanceId, String candidateUser);

	/**
	 * 任务组成员接管任务。
	 * 接管任务后，将组任务变成了个人的任务；另外，该小组的任何成员都不能使用条件taskCandidateUser(String
	 * candidateUser)查询到该任务，因为，任务被接管后，这个任务不再属于组任务，只属于个人的任务，使用条件taskAssignee(String
	 * userId)可以查询到
	 * 
	 * @param taskId
	 *            任务id
	 * @param userId
	 *            组成员标识
	 */
	public abstract void claim(String taskId, String userId);

	/**
	 * 归还任务给小组成员。
	 * 某某组成员接管任务后，还可以归还任务给小组成员，之后，组成员可以使用条件taskCandidateUser(String
	 * candidateUser)查询到组任务。
	 * 
	 * @param taskId
	 *            任务id
	 */
	public abstract void unclaim(String taskId);

	/**
	 * 查询流程的执行对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return 执行对象列表
	 */
	public abstract List<Execution> queryExecutionByProcDefId(String processDefinitionId);

	/**
	 * 查询流程的执行对象
	 * 
	 * @param processInstanceId
	 *            流程实例ID
	 * @return 执行对象列表
	 */
	public abstract List<Execution> queryExecutionByProcInstId(String processInstanceId);

	/**
	 * 办理任务
	 * 
	 * @param taskId
	 *            任务标识
	 */
	public abstract void completeTask(String taskId);

	/**
	 * 办理任务
	 * 
	 * @param taskId
	 *            任务标识
	 * @param map
	 *            流程变量
	 */
	public abstract void completeTask(String taskId, Map<String, Object> map);

	/**
	 * 查询流程的状态
	 * 
	 * @param processInstanceId
	 *            流程实例Id
	 * @return 流程的状态
	 */
	public abstract String queryProcessState(String processInstanceId);

	/**
	 * 通过任务查询流程变量
	 * 
	 * @param taskId
	 *            任务id
	 * @return 流程变量
	 */
	public abstract Map<String, Object> getVariablesFromTask(String taskId);

	/**
	 * 通过执行对象查询流程变量
	 * 
	 * @param executionId
	 *            执行对象id
	 * @return 流程变量
	 */
	public abstract Map<String, Object> getVariablesFromExecution(String executionId);

	/**
	 * 通过任务设置流程变量
	 * 
	 * @param taskId
	 *            任务id
	 * @param map
	 *            流程变量
	 */
	public abstract void setVariablesByTask(String taskId, Map<String, Object> map);

	/**
	 * 通过执行对象设置流程变量
	 * 
	 * @param executionId
	 *            执行对象id
	 * @param map
	 *            流程变量
	 */
	public abstract void setVariablesByExecution(String executionId, Map<String, Object> map);

	/**
	 * 通过任务设置自定义流程变量
	 * 
	 * @param taskId
	 *            任务id
	 * @param variableName
	 *            变量名
	 * @param value
	 *            自定义对象，要求该对象必须实现接口java.io.Serializable
	 */
	public abstract void setCustomVariableByTask(String taskId, String variableName, Object value);

	/**
	 * 通过执行对象设置自定义流程变量
	 * 
	 * @param executionId
	 *            执行对象id
	 * @param variableName
	 *            变量名
	 * @param value
	 *            自定义对象，要求该对象必须实现接口java.io.Serializable
	 */
	public abstract void setCustomVariableByExecution(String executionId, String variableName, Object value);

	/**
	 * 通过任务查询自定义变量
	 * 
	 * @param taskId
	 *            任务id
	 * @param variableName
	 *            变量名称
	 * @return 自定义变量对象
	 */
	public abstract Object getCustomVariableFromTask(String taskId, String variableName);

	/**
	 * 通过执行对象查询自定义变量
	 * 
	 * @param executionId
	 *            执行对象id
	 * @param variableName
	 *            变量名称
	 * @return 自定义变量对象
	 */
	public abstract Object getCustomVariableFromExecution(String executionId, String variableName);

	/**
	 * 查询历史流程实例
	 * 
	 * @param processDefinitionId
	 *            流程定义Id
	 * @return 历史流程实例列表
	 */
	public abstract List<HistoricProcessInstance> queryHistoricProcessInstanceByProcDefId(String processDefinitionId);

	/**
	 * 查询历史流程实例
	 * 
	 * @param processInstanceId
	 * @return 历史流程实例
	 */
	public abstract HistoricProcessInstance queryHistoricProcessInstanceByProcInstId(String processInstanceId);

	/**
	 * 查询流程的历史节点
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return 历史任务列表
	 */
	public abstract List<HistoricActivityInstance> queryHistoricActivityInstance(String processInstanceId);

	/**
	 * 查询流程的历史任务
	 * 
	 * @param processInstanceId
	 *            流程定义Id
	 * @return 流程的历史任务列表
	 */
	public abstract List<HistoricTaskInstance> queryHistoricTaskInstance(String processInstanceId);

	/**
	 * 查询任务办理人的历史任务
	 * 
	 * @param assignee
	 *            任务的办理人
	 * @return 流程的历史任务列表
	 */
	public abstract List<HistoricTaskInstance> queryHistoricTaskInstanceByAssignee(String assignee);

	/**
	 * 查询一支流程的历史流程变量
	 * 
	 * @param processInstanceId
	 *            流程实例Id
	 * @return 历史流程变量列表
	 */
	public abstract List<HistoricVariableInstance> queryHistoricVariableInstance(String processInstanceId);

	/**
	 * 查询组任务接管的历史成员列表
	 * 
	 * @param taskId
	 *            任务id
	 * @return 小组成员列表
	 */
	public abstract List<HistoricIdentityLink> queryHistoricIdentityLinksForTask(String taskId);

	/**
	 * 查询当前User Task的incoming sequence flow
	 * 
	 * @param proDefId
	 *            流程定义ID
	 * @param proInsId
	 *            流程实例ID
	 */
	public abstract String queryIncomingSequenceFlow(String proDefId, String proInsId);

	/**
	 * 查询当前User Task的上一个User Task定义的Key
	 * 
	 * @param processInstanceId
	 *            流程实例的ID
	 */
	public abstract String queryPreviousActivitiId(String processInstanceId);

	/**
	 * 查询当前活动的 User Task定义的Key
	 * 
	 * @param processInstanceId
	 *            流程实例的ID
	 */
	public abstract String queryCurrentActivitiId(String processInstanceId);

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
	public abstract List<String> querySequenceFlow(SequenceflowTypeEnum flowType, String processDefinitionId,
			String activityId);

	/**
	 * 向外部等待的活动实例发送外部触发器给定的执行(让执行对象向下执行)，主要用于Receive task节点类型，只有执行对象，没有任务
	 * 
	 * @param executionId
	 *            执行对象Id
	 */
	public abstract void signal(String executionId);

	/**
	 * 向外部等待的活动实例发送外部触发器给定的执行(让执行对象向下执行)，主要用于Receive task节点类型，只有执行对象，没有任务
	 * 
	 * @param executionId
	 *            执行对象Id
	 * @param processVariables
	 *            流程变量
	 */
	public abstract void signal(String executionId, Map<String, Object> processVariables);

}
