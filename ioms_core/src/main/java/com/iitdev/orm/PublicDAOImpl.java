package com.iitdev.orm;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.iitdev.exception.IitdevRuntimeException;
import com.iitdev.orm.analysis.AnalysisUtil;

/**
 * Title:数据库访问类<br/>
 * Description: <br/>
 * author Jerry <br/>
 * Create Date:2011-05-11 <br/>
 * Remark:<br/>
 **/
public abstract class PublicDAOImpl  implements
		PublicDAO {
	protected JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public <T> void delete(T entity) {
		Class<?> clazz =entity.getClass();
		Serializable id=AnalysisUtil.getPrimaryKeyValue(entity);
		deleteById(id, clazz);
	}

	public <T> void deleteById(final Serializable id, Class<T> clazz) {
		String tableName = AnalysisUtil.getTableName(clazz);
		String idName = AnalysisUtil.getPrimaryKey(clazz);
		String sql = "delete from "+tableName+" where "+idName+" = ?";
		jdbcTemplate.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement prestmt) throws SQLException {
				prestmt.setLong(1, (Long) id);
			}
		});
	}



	public <T> void save(T entity) {
		jdbcTemplate.update(AnalysisUtil.getExceSQL(entity.getClass(), AnalysisUtil.UpdateType.INSERT),
				AnalysisUtil.getPrestmtSetter(entity, AnalysisUtil.UpdateType.INSERT));
	}


	public <T> void update(T entity) {
		jdbcTemplate.update(AnalysisUtil.getExceSQL(entity.getClass(), AnalysisUtil.UpdateType.MODIFY),
				AnalysisUtil.getPrestmtSetter(entity, AnalysisUtil.UpdateType.MODIFY));
	}
	
	public <T> T getById(Serializable id ,Class<T> clazz){
		T entity  = null;
		if(id!=null){
			String tableName = AnalysisUtil.getTableName(clazz);
			String idName = AnalysisUtil.getPrimaryKey(clazz);
			String sql = "select * from "+tableName+" where "+idName+" = "+id;
			entity=queryForBean(clazz, sql, new Object[]{});
		}
		
		return entity;
	}
	
	@Override
	public void executeSql(String sql) {
		jdbcTemplate.execute(sql);
	}

	@Override
	public Map<String, Object> queryForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForMap(sql, args);
	}

	@Override
	public <T> List<T> queryForBeanList(Class<T> clazz, String sql,
			Object[] args) {
		RowMapper<T> rm = SpringBeanRowMapper.newInstance(clazz);
		List<T> result = getJdbcTemplate().query(sql, args, rm);
		return result == null ? new ArrayList<T>(0) : result;
	}

	@Override
	public <T> T queryForBean(Class<T> returnClazz, String sql,
			Object[] paramsValues) {
		RowMapper<T> rm = SpringBeanRowMapper.newInstance(returnClazz);
		List<T> results = jdbcTemplate.query(sql, rm, paramsValues);
		if (results == null || 0 == results.size()) {
			return null;
		}
		if (results.size() > 1) {
			throw new IitdevRuntimeException("返回结果不唯一");
		}
		return results.get(0);
	}

	@Override
	public Object queryForObject(String sql, Object[] paramsValues) {
		Map<String, Object> map = this.queryForMap(sql, paramsValues);
		if (map != null && !map.values().isEmpty()) {
			return map.values().iterator().next();
		}
		return null;
	}

	@Override
	public Integer queryForInt(String sql, Object[] paramsValues) {
		return jdbcTemplate.queryForInt(sql, paramsValues);
	}

	@Override
	public Long queryForLong(String sql, Object[] paramsValues) {
		return jdbcTemplate.queryForLong(sql, paramsValues);
	}

	@Override
	public <T> List<T> queryForList(String sql, Object[] args,
			Class<T> elementType) {
		return this.jdbcTemplate.queryForList(sql, args, elementType);
	}
}
