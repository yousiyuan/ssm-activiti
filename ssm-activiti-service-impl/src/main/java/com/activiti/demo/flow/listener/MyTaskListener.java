package com.activiti.demo.flow.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 通过监听类动态的为不同的流程图节点分配审批人
 */
public class MyTaskListener implements TaskListener {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 4853143724920164352L;

	/**
	 * 动态指定节点审批人
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		String taskKey = delegateTask.getTaskDefinitionKey();
		UserTaskNodes userTaskNode = UserTaskNodes.valueOf(taskKey.toUpperCase());
		if (userTaskNode == UserTaskNodes.UT_MGR_AUDIT) {
			delegateTask.setAssignee("流年公子");
		}
		if (userTaskNode == UserTaskNodes.UT_BOSS_AUDIT) {
			delegateTask.setAssignee("流年莉娜");
		}
	}

	/**
	 * 流程图节点枚举
	 */
	public enum UserTaskNodes {
		/**
		 * 经理审批节点
		 */
		UT_MGR_AUDIT("ut_mgr_audit"),
		/**
		 * 老板审批节点
		 */
		UT_BOSS_AUDIT("ut_boss_audit");

		private String value;

		public String getValue() {
			return this.value;
		}

		private UserTaskNodes(String value) {
			this.value = value;
		}
	}

}
