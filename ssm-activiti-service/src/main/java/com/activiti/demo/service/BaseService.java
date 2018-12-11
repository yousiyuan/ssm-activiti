package com.activiti.demo.service;

import java.util.List;
import java.util.Map;

public interface BaseService<TEntity> {

	/**
	 * 新增
	 * 
	 * @param object
	 *            实体对象
	 */
	public abstract void insertObject(TEntity object);

	/**
	 * 查询
	 * 
	 * @param primaryKey
	 *            主键
	 */
	public abstract TEntity queryForObject(Object primaryKey);

	/**
	 * 更新
	 * 
	 * @param object
	 *            实体对象
	 */
	public abstract void updateObject(TEntity object);

	/**
	 * 键值对更新
	 * 
	 * @param sqlId
	 *            T-SQL语句id
	 * @param map
	 *            参数列表
	 */
	public default void updateObject(String sqlId, Map<String, Object> map) {
	}

	/**
	 * 删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	public abstract void deleteObject(Object primaryKey);

	/**
	 * 集合查询
	 * 
	 * @param sqlId
	 *            T-SQL语句id
	 * @param object
	 *            实体对象
	 * @return 实体对象列表
	 * @throws Exception
	 *             主动抛出未实现异常
	 */
	public default List<TEntity> queryForList(String sqlId, TEntity object) throws Exception {
		throw new Exception("调用了未实现的方法");
	}

}
