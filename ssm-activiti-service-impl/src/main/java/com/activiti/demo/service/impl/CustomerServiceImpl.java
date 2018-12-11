package com.activiti.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.activiti.demo.dao.CustomersDao;
import com.activiti.demo.entity.Customers;
import com.activiti.demo.service.CustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customers> implements CustomerService {

	@Autowired
	private CustomersDao customerDao;

	public CustomerServiceImpl() {
		this.setInsertSqlId("mapper.CUSTOMERS.insertSelective");
		this.setUpdateSqlId("mapper.CUSTOMERS.updateByPrimaryKeySelective");
		this.setQuerySqlId("mapper.CUSTOMERS.selectByPrimaryKey");
		this.setDeleteSqlId("mapper.CUSTOMERS.deleteByPrimaryKey");
	}

	@Override
	public List<Customers> queryForList(String sqlId, Customers object) throws Exception {
		logger.debug("query list parameter sqlId：" + sqlId + ", object：" + JSON.toJSONString(object,
				SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames));
		return customerDao.queryForList(sqlId, object);
	}

}
