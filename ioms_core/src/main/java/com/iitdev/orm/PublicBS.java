package com.iitdev.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;




public interface PublicBS {
	
	/**
	 * 获取当前时间
	 * @return 当前时间
	 */
	public Date getCurrentTime();

	/**
	 * 持久化数据,保存
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T> void saveObject(T entity);

	/**
	 * 持久化数据,修改
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T> void updateObject(T entity);

	/**
	 * 修改数据 remark:把get出来的对象设置一个要更改的值 然后update(这个对象)
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T> void delete(T entity);

	/**
	 * 根据ID删数据
	 * 
	 * @param <T>
	 * @param id
	 * @param clazz
	 */
	public <T> void deleteById(Serializable id, Class<T> clazz);


	/**********************/
	/******* 查询操作 *******/
	public <T> T getById(Serializable id ,Class<T> clazz);

	/******** 跟数据库有关 *********/
	/******* 存储过程 *******/

	/**
	 * 调用存储过程返回游标数据
	 * 
	 * @param storeName
	 *            存储过程
	 * @param paramValues
	 *            入参,参数顺序排好
	 * @return 返回一个list内容包括Map,目前暂不支持
	 * @deprecated 目前暂不支持该方法
	 */
	@Deprecated
	public List<Map<String, Object>> callForList(final String storeName,
			Object[] paramValues);

	/**
	 * 调用存储过程返回单指
	 * 
	 * @param storeName
	 *            存储过程
	 * @param paramValues
	 * @param sqlType
	 *            返回的out类型如:java.sql.Types.Varchar; 注意如果是数字类型，返回都是BigDecimal类型
	 * @return 返回一个Obj
	 * @deprecated 目前暂不支持该方法
	 */
	@Deprecated
	public Object callForObj(final String storeName, Object[] paramValues,
			int sqlType);

	/**
	 * 无返回值的存储过程
	 * 
	 * @param storeName
	 * @param paramValues
	 * @deprecated 目前暂不支持该方法
	 */
	@Deprecated
	public void callForObj(final String storeName, Object[] paramValues);

	/**
	 * 获取下个sequence的值
	 * 
	 * @param seqName
	 * @return 返回sequence值,mysql暂不支持
	 */
	@Deprecated
	public Long getNextSequence(String seqName);

	/**
	 * function:JDBC执行sql语句 remark:直接使用JDBC
	 * 
	 * @param sql
	 *            sql语句
	 */
	public void executeSql(String sql);

	/**
	 * function:取得MAP remark:直接使用JDBC
	 * 
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数列表
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args);

	/**
	 * function:取得List remark:直接使用JDBC
	 * 
	 * @param clazz 
	 * 			 为了控制查询的list的返回值,返回的应当是传入的clazz的集合对象 
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数列表
	 */
	public <T> List<T> queryForBeanList(Class<T> clazz, String sql,
			Object[] args);

	/**
	 * 查询单个类对象
	 * 
	 * @param <T>
	 * @param returnClazz
	 *            查询的类
	 * @param sql
	 *            查询语句
	 * @param paramsValues
	 *            查询参数
	 * @return 返回实体类
	 */
	public <T> T queryForBean(Class<T> returnClazz, String sql,
			Object[] paramsValues);



	public Object queryForObject(String sql, Object[] paramsValues);

	public Integer queryForInt(String sql, Object[] paramsValues);

	public Long queryForLong(String sql, Object[] paramsValues);

	public <T> List<T> queryForList(String sql, Object[] args,
			Class<T> elementType);

	
	/**
	 * 满足用户自定义
	 * 首先要实例化MysqlSqlBuilder,后最后build一下即可
	 * select 不能写group by 
	 * 条件添加未测试,要手动加入条件参数
	 * 要分页的话直接使用即可
	 * @param sqlBuilder MYSQL的数据库SQL拼接工具类  <code>MysqlSqlBuilder</code>
	 * @param paramValues 参数
	 * @return 返回一个结果集
	 */
	public<T> List<T> querySqlForAndList(MysqlSqlBuilder sqlBuilder,Object[] paramValues);
	
	/**
	 * @param sqlBuilder MYSQL的数据库SQL拼接工具类  <code>MysqlSqlBuilder</code>
	 * @return 返回List集合或者是分页封装类Page
	 */
	public <T> Object querySqlForPage(MysqlSqlBuilder sqlBuilder);
	
}
