package com.activiti.demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.activiti.demo.dao.BaseDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class BaseDaoImpl<TEntity> extends SqlSessionDaoSupport implements BaseDao<TEntity> {

	protected static Logger logger = LogManager.getLogger();

	@Autowired
	protected SqlSession sqlSession;

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void insertObject(String sqlId, TEntity object) {
		logger.debug("insert parameter sqlId:" + sqlId + ", object：" + JSON.toJSONString(object,
				SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames));
		sqlSession.insert(sqlId, object);
	}

	@Override
	public TEntity queryForObject(String sqlId, Object primaryKey) {
		logger.debug("query parameter sqlId：" + sqlId + ", primaryKey：" + primaryKey);
		return sqlSession.selectOne(sqlId, primaryKey);
	}

	@Override
	public void updateObject(String sqlId, TEntity object) {
		logger.debug("update parameter sqlId：" + sqlId + ", object：" + JSON.toJSONString(object,
				SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames));
		sqlSession.update(sqlId, object);
	}

	@Override
	public void updateObject(String sqlId, Map<String, Object> map) {
		logger.debug("update parameter sqlId：" + sqlId + ", map："
				+ JSON.toJSONString(map, SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames));
		sqlSession.update(sqlId, map);
	}

	@Override
	public void deleteObject(String sqlId, Object primaryKey) {
		logger.debug("delete parameter sqlId：" + sqlId + ", primaryKey：" + primaryKey);
		sqlSession.delete(sqlId, primaryKey);
	}

	@Override
	public List<TEntity> queryForList(String sqlId, TEntity object) {
		logger.debug("querylist parameter sqlId:" + sqlId + ", object：" + JSON.toJSONString(object,
				SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames));
		return sqlSession.selectList(sqlId, object);
	}

}
