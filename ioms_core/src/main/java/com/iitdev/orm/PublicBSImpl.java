package com.iitdev.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iitdev.exception.IitdevRuntimeException;

public class PublicBSImpl implements PublicBS {
	@Resource
	protected PublicDAO publicDAO;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public Date getCurrentTime() {
		return new Date();
	}


	protected JdbcTemplate getJdbcTemplate() {
		return publicDAO.getJdbcTemplate();
	}

	public <T> void saveObject(T entity) {
		validateAdd(entity);
		publicDAO.save(entity);
	}

	public <T> void updateObject(T entity) {
		validateModify(entity);
		publicDAO.update(entity);
	}

	public <T> void delete(T entity) {
		validateDelete(entity);
		publicDAO.delete(entity);
	}

	public <T> void deleteById(Serializable id, Class<T> clazz) {
		publicDAO.deleteById(id, clazz);
	}
	public <T> T getById(Serializable id ,Class<T> clazz){
		return publicDAO.getById(id, clazz);
	}


	// public PageData findForPage(String hql, Map<String, Object> pmap,
	// int topage, int pagesize) {
	// return publicDAO.findForPage(hql, pmap, topage, pagesize);
	// }

	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> callForList(String storeName,
			Object[] paramValues) {
		return publicDAO.callForList(storeName, paramValues);
	}

	@SuppressWarnings("deprecation")
	public Object callForObj(String storeName, Object[] paramValues, int sqlType) {
		return publicDAO.callForObj(storeName, paramValues, sqlType);
	}

	@SuppressWarnings("deprecation")
	public void callForObj(String storeName, Object[] paramValues) {
		publicDAO.callForObj(storeName, paramValues);
	}

	@SuppressWarnings("deprecation")
	public Long getNextSequence(String seqName) {
		return publicDAO.getNextSequence(seqName);
	}

	public void executeSql(String sql) {
		publicDAO.executeSql(sql);
	}

	public Map<String, Object> queryForMap(String sql, Object[] args) {
		return publicDAO.queryForMap(sql, args);
	}

	public <T> List<T> queryForBeanList(Class<T> clazz, String sql,
			Object[] args) {
		return publicDAO.queryForBeanList(clazz, sql, args);
	}

	public <T> T queryForBean(Class<T> returnClazz, String sql,
			Object[] paramsValues) {
		return publicDAO.queryForBean(returnClazz, sql, paramsValues);
	}

	// public <T> PageData queryForPageData(String sql, Class<T> clazz,
	// int topage, int pagesize, Object[] paramsValues) {
	// return publicDAO.queryForPageData(sql, clazz, topage, pagesize,
	// paramsValues);
	// }

	public Object queryForObject(String sql, Object[] paramsValues) {
		return publicDAO.queryForObject(sql, paramsValues);
	}

	public Integer queryForInt(String sql, Object[] paramsValues) {
		return publicDAO.queryForInt(sql, paramsValues);
	}

	public Long queryForLong(String sql, Object[] paramsValues) {
		return publicDAO.queryForLong(sql, paramsValues);
	}

	public <T> List<T> queryForList(String sql, Object[] args,
			Class<T> elementType) {
		return publicDAO.queryForList(sql, args, elementType);
	}


	protected void validateAdd(Object obj) throws IitdevRuntimeException {
	}

	protected void validateModify(Object obj) throws IitdevRuntimeException {
	}

	protected void validateDelete(Object obj) throws IitdevRuntimeException {
	}

	/**
	 * 满足用户自定义 首先要实例化MysqlSqlBuilder,后最后build一下即可 select 不能写group by
	 * 条件添加未测试,要手动加入条件参数 要分页的话直接使用即可
	 * 
	 * @param sqlBuilder
	 *            MYSQL的数据库SQL拼接工具类 <code>MysqlSqlBuilder</code>
	 * @param paramValues
	 *            参数
	 * @return 返回一个结果集
	 */
	public <T> List<T> querySqlForAndList(MysqlSqlBuilder sqlBuilder,
			Object[] paramValues) {
		return publicDAO.querySqlForAndList(sqlBuilder, paramValues);
	}

	/**
	 * @param sqlBuilder
	 *            MYSQL的数据库SQL拼接工具类 <code>MysqlSqlBuilder</code>
	 * @return 返回List集合或者是分页封装类Page
	 */
	public <T> Object querySqlForPage(MysqlSqlBuilder sqlBuilder) {
		return publicDAO.querySqlForPage(sqlBuilder);
	}
}
