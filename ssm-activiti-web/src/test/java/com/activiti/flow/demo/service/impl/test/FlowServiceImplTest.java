package com.activiti.flow.demo.service.impl.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.activiti.demo.entity.Products;
import com.activiti.demo.flow.service.FlowService;
import com.activiti.demo.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class FlowServiceImplTest {

	@Autowired
	private FlowService flowService;

	@Autowired
	private ProductService productService;

	@Before
	public void setUp() {
	}

	@Test
	public void bussinessTest() {
		Products obj = productService.queryForObject(11);

		System.out.println("\r\n||||||||||||||||||||||||||||||||||||||||\r\n");
		System.out.println(obj);
		System.out.println("\r\n||||||||||||||||||||||||||||||||||||||||\r\n");
	}

	// 部署流程
	@Test
	public void deployTest() {
		/**
		 * SELECT * FROM act_re_deployment; SELECT * FROM act_re_procdef; SELECT
		 * * FROM act_ge_bytearray;
		 */
		flowService.deployProcess("diagrams/p_entry_apply.zip", "员工入职申请流程", "人事管理");
	}

	// 删除流程部署
	@Test
	public void unDeployTest() {
		flowService.undeployProcess("15001", true);
		flowService.undeployProcess("17501", true);
	}

}
