package com.activiti.demo.service.impl;

import org.springframework.stereotype.Service;

import com.activiti.demo.entity.OrderDetails;
import com.activiti.demo.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetails> implements OrderDetailService {

	public OrderDetailServiceImpl() {
		this.setInsertSqlId("mapper.ORDERDETAILS.insertSelective");
		this.setUpdateSqlId("mapper.ORDERDETAILS.updateByPrimaryKeySelective");
		this.setQuerySqlId("mapper.ORDERDETAILS.selectByPrimaryKey");
		this.setDeleteSqlId("mapper.ORDERDETAILS.deleteByPrimaryKey");
	}

}
