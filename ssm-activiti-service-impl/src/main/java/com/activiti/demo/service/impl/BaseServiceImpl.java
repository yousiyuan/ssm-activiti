package com.activiti.demo.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.activiti.demo.dao.BaseDao;
import com.activiti.demo.service.BaseService;

public abstract class BaseServiceImpl<TEntity> implements BaseService<TEntity> {

	protected static Logger logger = LogManager.getLogger();

	@Autowired
	private BaseDao<TEntity> baseDao;

	@Override
	public void insertObject(TEntity object) {
		logger.debug("insert parameter：" + JSON.toJSONString(object, SerializerFeature.WRITE_MAP_NULL_FEATURES,
				SerializerFeature.QuoteFieldNames));
		baseDao.insertObject(this.getInsertSqlId(), object);
	}

	@Override
	public TEntity queryForObject(Object primaryKey) {
		logger.debug("query parameter：" + primaryKey);
		return baseDao.queryForObject(this.getQuerySqlId(), primaryKey);
	}

	@Override
	public void updateObject(TEntity object) {
		logger.debug("update parameter：" + JSON.toJSONString(object, SerializerFeature.WRITE_MAP_NULL_FEATURES,
				SerializerFeature.QuoteFieldNames));
		baseDao.updateObject(this.getUpdateSqlId(), object);
	}

	@Override
	public void deleteObject(Object primaryKey) {
		logger.debug("delete parameter：" + primaryKey);
		baseDao.deleteObject(this.getDeleteSqlId(), primaryKey);
	}

	private String insertSqlId;
	private String querySqlId;
	private String updateSqlId;
	private String deleteSqlId;

	public String getInsertSqlId() {
		return insertSqlId;
	}

	public void setInsertSqlId(String insertSqlId) {
		this.insertSqlId = insertSqlId;
	}

	public String getQuerySqlId() {
		return querySqlId;
	}

	public void setQuerySqlId(String querySqlId) {
		this.querySqlId = querySqlId;
	}

	public String getUpdateSqlId() {
		return updateSqlId;
	}

	public void setUpdateSqlId(String updateSqlId) {
		this.updateSqlId = updateSqlId;
	}

	public String getDeleteSqlId() {
		return deleteSqlId;
	}

	public void setDeleteSqlId(String deleteSqlId) {
		this.deleteSqlId = deleteSqlId;
	}

}
