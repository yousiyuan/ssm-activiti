package com.activiti.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.activiti.demo.dao.ProductsDao;
import com.activiti.demo.entity.Products;

@Repository
public class ProductsDaoImpl extends BaseDaoImpl<Products> implements ProductsDao {

	@Override
	public int count(String sqlId) {
		logger.debug("count parameter sqlId：" + sqlId);
		return sqlSession.selectOne(sqlId);
	}

}
