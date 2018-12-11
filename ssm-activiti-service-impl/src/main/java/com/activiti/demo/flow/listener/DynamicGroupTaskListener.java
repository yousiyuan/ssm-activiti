package com.activiti.demo.flow.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 自定义监听器，动态地为组任务分配若干个可能的受理人
 */
public class DynamicGroupTaskListener implements TaskListener {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -2358667510106239551L;

	@Override
	public void notify(DelegateTask delegateTask) {
		// 添加组成员
		delegateTask.addCandidateUser("java");
		delegateTask.addCandidateUser("C#");
		delegateTask.addCandidateUser("C++");
		delegateTask.addCandidateUser("python");
		// 删除组成员
		// delegateTask.deleteCandidateUser(userId);
	}

	/**
	 * process map nodes enum
	 */
	public enum UserTaskNodes {
		/**
		 * 客服服务中心
		 */
		UT_USER_SERVICE("ut_user_service");
		
		/**
		 * enum value
		 */
		private String value;
		/**
		 * get enum value
		 */
		public String getValue() {
			return this.value;
		}
		/**
		 * enum constructor
		 */
		private UserTaskNodes(String value) {
			this.value = value;
		}
	}
	
}
