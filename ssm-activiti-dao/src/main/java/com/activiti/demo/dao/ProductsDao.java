package com.activiti.demo.dao;

import com.activiti.demo.entity.Products;

public interface ProductsDao extends BaseDao<Products> {

	public abstract int count(String sqlId);

}
