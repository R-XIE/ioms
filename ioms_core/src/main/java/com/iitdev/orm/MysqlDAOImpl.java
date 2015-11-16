package com.iitdev.orm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.iitdev.page.Page;
import com.iitdev.page.PageUtil;
import com.iitdev.page.Result;


//TODO 
public class MysqlDAOImpl extends PublicDAOImpl {
    
    @Deprecated
    public Long getNextSequence(String seqName) {
	Long lSql = Long.valueOf(0);
	String seqsql = "select " + seqName + ".nextval from dual";
	lSql = jdbcTemplate.queryForLong(seqsql);
	return lSql;
    }
    
    @Deprecated
    public List<Map<String, Object>> callForList(String spName,
	    Object[] paramValues) {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Deprecated
    public Object callForObj(String spName, Object[] paramValues, int sqlType) {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Deprecated
    public void callProcedure(String storeName, Object[] params) {
	// TODO Auto-generated method stub

    }

    @Override
    @Deprecated
    public void callForObj(String storeName, Object[] paramValues) {
    }

	
    @SuppressWarnings("unchecked")
	public <T> List<T> querySqlForAndList(MysqlSqlBuilder sqlBuilder,Object[] paramValues){
    	RowMapper<T> rm = SpringBeanRowMapper.newInstance(sqlBuilder.getQueryClazz());
    	List<T> list =  this.jdbcTemplate.query(sqlBuilder.build(), paramValues,rm);    	
    	return list;
    }
    
    @SuppressWarnings({ "static-access", "unchecked" })
	public <T> Object querySqlForPage(MysqlSqlBuilder sqlBuilder){    	
    	Object[] paramValues=sqlBuilder.getParams();
    	if(sqlBuilder.getListType()==sqlBuilder.LIST_TYPE_LIST){
    		String sql= sqlBuilder.build();
    		List<T> list=null;
    		if(sqlBuilder.getRowMapper()!=null){
    			list=this.getJdbcTemplate().query(sql, paramValues, sqlBuilder.getRowMapper());
    		}else{
    			list=this.queryForBeanList(sqlBuilder.getQueryClazz(), sql, paramValues);
    		}
    		return list==null?new ArrayList<T>(0):list;
    	}else if(sqlBuilder.getListType()==sqlBuilder.LIST_TYPE_PAGE){
    		Result<T> result = new Result<T>();
    		String countSql= sqlBuilder.getCountSql();
    		int totalCount = this.queryForInt(countSql, paramValues);
    		Page page = PageUtil.getInstance(sqlBuilder.getPageSize()).
    				createPage(totalCount, sqlBuilder.getCurrPage());
    		result.setPage(page);
    		sqlBuilder.createLimit(page.getBeginIndex(), page.getEveryPageNum());
    		List<T> list =null;
    		String sql = sqlBuilder.build();
    		list= queryForBeanList(sqlBuilder.getQueryClazz(), sql, paramValues);
    		result.setList(list);
    		return result;
    	}
    	return null;
    }

}
