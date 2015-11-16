package com.iitdev.orm;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.RowMapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iitdev.exception.IitdevException;
import com.iitdev.exception.IitdevRuntimeException;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.utils.Util;

/**
 * android部分
 * 要分页的 话只需要设置beginInde 和 everyPage 调用createLimit方法
 * 不分页直接build()即可
 * J2EE部分
 * 分页只需设置currPage
 * 设置别名请勿使用dCount,ncdCount当作别名 默认为list 如果list_type 为page则分页,list则为集合
 * 
 * @author Jerry
 * 
 */
public class MysqlSqlBuilder {

	private StringBuffer selectSql = new StringBuffer("");
	private StringBuffer whereSql = new StringBuffer("");
	private StringBuffer orderSql = new StringBuffer("");
	private StringBuffer groupSql = new StringBuffer("");
	private StringBuffer limitSql = new StringBuffer("");

	public static final int LIST_TYPE_PAGE = 1;// 查询分页，返回Page
	public static final int LIST_TYPE_LIST = 0;// 查询list，返回list

	public int listType = LIST_TYPE_LIST; // 默认查询的是LIST

	/**
	 * android 所用
	 */
	private int beginIndex = 0;
	private int pageSize = PropertiesConstant.getPageSize();
	
	/**
	 * J2EE所用
	 */
	private int currPage=1;
	private Map<String, ? extends Object> queryParams = Maps.newHashMap();
	private List<Object> paramValues = Lists.newArrayList();

	private Class<?> queryClazz;// 查询的Class，一般是VO
	private RowMapper<?> rowMapper = null;

	/**
	 * 允许的时间转换格式
	 */
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd") }; // 支持转换的日期格式

	/**
	 * 将obj转换为date类型
	 * 
	 * @param value
	 * @return
	 */
	private static Date convertToDate(Object value) {
		Date date = null;
		String dateString = null;
		dateString = String.valueOf(value);
		for (DateFormat formate : ACCEPT_DATE_FORMATS) {
			try {
				date = formate.parse(dateString);
				return date;
			} catch (Exception e) {
				continue;
			}
		}
		if (date == null)
			throw new IitdevRuntimeException("MysqlBuilder:日期转换失败" + value);
		return null;
	}

	/**
	 * 将obj转换为list集合
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List convertToList(Object value, Class javaType) {
		try {
			List result = null;
			String val = String.valueOf(value);
			String[] vals = val.split(",");
			result = new ArrayList(val.length());
			for (String string : vals) {
				if (Long.class.equals(javaType)) {
					result.add(Long.parseLong(string));
					continue;
				}
				result.add(string);
			}
			return result;
		} catch (Exception e) {
			throw new IitdevRuntimeException("集合解析失败！" + value.toString());
		}
	}

	/**
	 * 设置select，使用后清除原有的select数据
	 * 
	 * @param selectSql
	 */
	public void setSelectSql(String selectSql) {
		this.selectSql.delete(0, selectSql.length());
		this.selectSql.append(selectSql);
	}

	/**
	 * 设置order by后的数据
	 * 
	 * @param orderSql
	 */
	public void setOrderSql(String orderSql) {
		this.orderSql = new StringBuffer(orderSql);
	}

	/**
	 * 设置group by的数据
	 * 
	 * @param groupSql
	 */
	public void setGroupSql(String groupSql) {
		this.groupSql = new StringBuffer(groupSql);
	}

	/**
	 * 对应？ 相应的查询参数值
	 * 
	 * @return
	 */
	public Object[] getParams() {
		Object[] result = new Object[paramValues.size()];
		paramValues.toArray(result);
		return result;
	}

	public MysqlSqlBuilder() {
	}

	/**
	 * 构造函数
	 * 
	 * @param selectSql
	 */
	public MysqlSqlBuilder(Class<?> queryClazz, String selectSql) {
		this.queryClazz=queryClazz;
		this.selectSql = new StringBuffer(selectSql);
	}

	/**
	 * 带条件参数的构造函数
	 * 
	 * @param selectSql
	 * @param queryParams
	 */
	public MysqlSqlBuilder(Class<?> queryClazz, String selectSql,
			Map<String, ? extends Object> queryParams) {
		this.queryClazz=queryClazz;
		this.selectSql = new StringBuffer(selectSql);
		this.setQueryParams(queryParams);//去除空值
	}

	/**
	 * 在使用本类最后，分页之前使用该方法（必要的方法）
	 * 
	 * @throws IitdevException
	 */
	public String build() {
		String selectSql = this.selectSql.toString();
		String whereSql = this.whereSql.toString();
		String orderSql = this.orderSql.toString();
		String groupSql = this.groupSql.toString();
		StringBuilder sql = new StringBuilder();
		if (Util.isNull(selectSql))
			try {
				throw new IitdevException("没有选择SELECT子句");
			} catch (IitdevException e) {
				e.printStackTrace();
			}
		sql.append(selectSql);
		if (StringUtils.isNotBlank(whereSql.toString())) {
			if (!isSelectHasWhere()) {
				sql.append(" where  ");
			}
			sql.append(whereSql.toString());
		}

		if (Util.isNotNull(groupSql) && !isGroupHasGroup(groupSql)) {
			groupSql = " group by " + groupSql;
		}

		if (Util.isNotNull(orderSql) && !isOrderHasOrder(orderSql)) {
			orderSql = " order by " + orderSql;
		}

		if (Util.isNull(groupSql))
			groupSql = "";

		if (Util.isNull(orderSql))
			orderSql = "";

		this.selectSql = new StringBuffer(selectSql);
		this.groupSql = new StringBuffer(groupSql);
		this.whereSql = new StringBuffer(whereSql);
		this.orderSql = new StringBuffer(orderSql);

		sql.append(" ").append(groupSql);
		sql.append(" ").append(orderSql);
		sql.append(" ").append(this.limitSql);
		return sql.toString();
	}

	/**
	 * 设置查询参数，不是必须,如果有值必须在addCondition前执行
	 * 
	 * @param queryParams
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setQueryParams(Map queryParams) {
		if (queryParams != null) {
			this.queryParams.putAll(Util.clearBlankValue(queryParams));
		}
	}

	/**
	 * 判断select子句中是否存在where关键字
	 * 
	 * @return
	 */
	private boolean isSelectHasWhere() {
		String sql = this.selectSql.toString().trim().toString().toLowerCase();// 全部转换为小写
		if (sql.indexOf("where") > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断order子句中是否存在from关键字
	 * 
	 * @param orderSql
	 * @return
	 */
	private boolean isOrderHasOrder(String orderSql) {
		String sql = orderSql.trim().toString().toLowerCase();// 全部转换为小写
		if (sql.indexOf("order by") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断group by子句中是否存在from关键字
	 * 
	 * @param groupSql
	 * @return
	 */
	private boolean isGroupHasGroup(String groupSql) {
		String sql = groupSql.trim().toString().toLowerCase();// 全部转换为小写
		if (sql.indexOf("group by") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 设置limit 的第一个参数
	 * 
	 * @param beginIndex
	 */
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	/**
	 * 获取limit的第一个参数
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * 设置limit的第二个参数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize <= 0)
			pageSize = PropertiesConstant.getPageSize();
		this.pageSize = pageSize;
	}

	/**
	 * 获取pageSize
	 * 
	 * @return 返回每页的数量
	 */
	public int getPageSize() {
		if (pageSize <= 0)
			pageSize = PropertiesConstant.getPageSize();
		return pageSize;
	}

	public int getListType() {
		return listType;
	}

	public void setListType(int listType) {
		this.listType = listType;
	}

	/**
	 * 获取当前页数
	 * 
	 * @return
	 */
	public int getCurrPage() {
		
		return this.currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	/**
	 * 操作符类型,用于SQL条件的操作符
	 * 
	 * @author cuisongliu
	 * 
	 */
	public static enum OperatorType {
		EQ("="), NEQ("!="), LIKE_ANYWHERE("like"), LIKE_START("like"), LIKE_END(
				"like"), GT(">"), GE(">="), LT("<"), LE("<="), ISNULL("is null"), ISNOTNULL(
				"is not null"), IN("in"), NOTIN("not in");
		private String operator;

		public String getOperator() {
			return " " + operator + " ";
		}

		private OperatorType(String operator) {
			this.operator = operator;
		}
	}

	public static enum ParamType {
		/**
		 * 此处为基本的数据类型
		 */
		STRING(java.sql.Types.VARCHAR, String.class), INTEGER(
				java.sql.Types.INTEGER, Integer.class), LONG(
				java.sql.Types.NUMERIC, Long.class), DECIMAL(
				java.sql.Types.DECIMAL, BigDecimal.class), DATE(
				java.sql.Types.DATE, Date.class),
		/**
		 * 此处为集合数据类型，用逗号分隔long集合
		 */
		LONG_ARRAY(java.sql.Types.NUMERIC, Long.class),
		/**
		 * 此处为集合数据类型，用逗号分隔string集合
		 */
		STRING_ARRAY(java.sql.Types.VARCHAR, String.class);
		@SuppressWarnings("unused")
		private int sqlType;
		private Class<?> javaType;

		private ParamType(int sqlType, Class<?> javaType) {
			this.sqlType = sqlType;
			this.javaType = javaType;
		}
	}

	/**
	 * 添加查询条件,默认 匹配条件 eq ，参数值类型不需要转换
	 * 
	 * @param propName
	 *            查询框名称（如：tcCountName)
	 * @param column_name
	 *            相应的数据库字段名称(如：b.tc_couont_Name)
	 * @return
	 */
	public MysqlSqlBuilder addCondition(String propName, String column_name) {
		return addCondition(propName, column_name, OperatorType.EQ, null);
	}

	/**
	 * 添加查询条件,默认 匹配条件 eq
	 * 
	 * @param propName
	 *            查询框名称（如：tcCountId)
	 * @param column_name
	 *            相应的数据库字段名称(如：b.tc_couont_id)
	 * @param dataType
	 *            需要转换的类型(如：ParameType.LONG,后台将自动转成Long类型的参数值）
	 * @return
	 */
	public MysqlSqlBuilder addCondition(String propName, String column_name,
			ParamType dataType) {
		return addCondition(propName, column_name, OperatorType.EQ, dataType);
	}

	/**
	 * 添加查询条件,参数值类型不需要转换
	 * 
	 * @param propName
	 *            查询框名称（如：tcCountName)
	 * @param column_name
	 *            相应的数据库字段名称(如：b.tc_couont_Name)
	 * @param operatorType
	 *            匹配条件(如:conditionType.LIKE_START 后台将会生成如 :b.tc_couont_Name like
	 *            '把手%')
	 * @return
	 */
	public MysqlSqlBuilder addCondition(String propName, String column_name,
			OperatorType operatorType) {
		return addCondition(propName, column_name, operatorType, null);
	}

	/**
	 * 添加查询条件 如果匹配条件为 in/notin 参数传list即可 若要匹配时间段则请用
	 * <code>addDateInCondition<code>
	 * 
	 * @param propName
	 *            查询框名称（如：tcCountName)
	 * @param column_name
	 *            相应的数据库字段名称(如：b.tc_couont_Name)
	 * @param operatorType
	 *            匹配条件(如:conditionType.GE 后台将会生成如 :b.tc_couont_Name >= '1' )
	 * @param paramType
	 *            需要转换的类型(如：ParameType.LONG,后台将自动转成Long类型的参数值）
	 * @return
	 */
	public MysqlSqlBuilder addCondition(String propName, String colum_name,
			OperatorType operatorType, ParamType paramType) {
		if (Util.isNull(propName))
			return this;
		for (Entry<String, ? extends Object> entry : queryParams.entrySet()) {
			if (propName.equals(entry.getKey())) {
				if (!(entry.getValue() instanceof String)) {// 参数类型为string
					buildConditonSql(operatorType, colum_name,
							entry.getValue(), null);
				} else {
					buildConditonSql(operatorType, colum_name,
							entry.getValue(), paramType);
				}
			}
		}
		return this;
	}

	private void buildConditonSql(OperatorType operatorType, String colum_name,
			Object value, ParamType paramType) {
		StringBuffer str = new StringBuffer();
		if (Util.isNotNull(whereSql.toString()) || isSelectHasWhere()) {
			str.append(" and ");
		}
		Object columnValue = formatColumnValue(value, paramType);
		switch (operatorType) {
		case LIKE_START: {
			str.append(colum_name).append(operatorType.getOperator())
					.append("?");
			paramValues.add("%" + columnValue);
			break;
		}
		case LIKE_END: {
			str.append(colum_name).append(operatorType.getOperator())
					.append("?");
			paramValues.add(columnValue + "%");
			break;
		}
		case LIKE_ANYWHERE: {
			str.append(colum_name).append(operatorType.getOperator())
					.append("?");
			paramValues.add("%" + columnValue + "%");
			break;
		}
		case ISNULL:
		case ISNOTNULL: {
			str.append(colum_name).append(operatorType.getOperator());
			break;
		}
		case IN:
		case NOTIN: {
			str.append(colum_name).append(operatorType.getOperator())
					.append("(");
			List<?> pList = (List<?>) columnValue;
			for (Iterator<?> iterator = pList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				if (iterator.hasNext()) {
					str.append("?,");
				} else {
					str.append("?)");
				}
				paramValues.add(object);
			}
			break;
		}
		default: {
			str.append(colum_name).append(operatorType.getOperator())
					.append("?");
			paramValues.add(columnValue);
			break;
		}
		}
		this.whereSql.append(str);
	}

	/**
	 * 用于时间区间查询的
	 * 
	 * @param dateStartPorp
	 *            起始时间
	 * @param dateEndPorp
	 *            结束时间
	 * @param columnName
	 *            字段名
	 * @param paramType
	 *            传入Date
	 * @return
	 */
	public MysqlSqlBuilder addDateInCondition(String dateStartPorp,
			String dateEndPorp, String columnName, ParamType paramType) {
		StringBuffer str = new StringBuffer();
		Date start = null;
		Date end = null;
		Object objStart = queryParams.get(dateStartPorp);
		Object objEnd = queryParams.get(dateEndPorp);
		if (objStart != null || objEnd != null) {
			if (objStart != null) {
				start = (Date) formatColumnValue(objStart, paramType);
				str.append(columnName).append(" >= ?");
				paramValues.add(start);
			}
			if (objEnd != null) {
				end = (Date) formatColumnValue(objEnd, paramType);
				// 如果日期达到了 0：00：00改为 23：59：59,因为时间会默认跳转到0 ：00 ：00
				Boolean isSame = DateUtils.isSameInstant(end,
						Util.getDateBegin(end));
				if (isSame) {
					end = Util.getDateEnd(end);
				}
				if (str.length() > 0) {
					str.append(" and ");
				}
				str.append(columnName).append(" <= ? ");
				paramValues.add(end);
			}
			str = new StringBuffer("(" + str.toString() + ")");
			if (Util.isNotNull(whereSql.toString()) || isSelectHasWhere()) {
				str = new StringBuffer(" and " + str.toString());
			}
		}
		whereSql.append(str);
		return this;
	}

	/**
	 * 自定义添加的限定条件 入:(a.rowStauts !=-1 or b.rowStauts != ?)这种较为复杂的 条件 如有in(?)
	 * 请使用addSqlInCondition
	 * 
	 * @param condtionSql
	 *            eg: (a.rowStauts !=-1 or b.rowStauts != ?)
	 * @param column_value
	 *            已经自己处理过类型的参数值（如：Date）
	 * @return
	 */
	public MysqlSqlBuilder addSqlCondition(String conditionSql,
			Object... column_value) {
		if (Util.isNotNull(whereSql.toString()) || isSelectHasWhere()) {
			whereSql.append(" and ");
		}
		whereSql.append(" ").append(conditionSql).append(" ");
		if (column_value != null && column_value.length > 0) {
			for (int i = 0; i < column_value.length; i++) {
				paramValues.add(column_value[i]);
			}
		}
		return this;
	}

	public MysqlSqlBuilder addSqlInCondition(String condtionSql,
			Collection<?> column_value) {
		if (column_value != null && !column_value.isEmpty()) {
			if (StringUtils.isNotBlank(whereSql.toString())
					|| isSelectHasWhere()) {
				whereSql.append(" and ");
			}
			StringBuilder bg = new StringBuilder(condtionSql.substring(0,
					condtionSql.indexOf("?")));
			for (Iterator<?> ite = column_value.iterator(); ite.hasNext();) {
				Object value = ite.next();
				if (ite.hasNext()) {
					bg.append("?,");
				} else {
					bg.append("?)");
				}
				paramValues.add(value);
			}
			whereSql.append(bg);
		}
		return this;
	}

	/**
	 * 参数值的类型转换 界面值一般传入都是String，这里根据提供的类型进行转换
	 * 
	 * @param value
	 * @param operatorType
	 * @return
	 */
	private Object formatColumnValue(Object value, ParamType paramType) {
		if (paramType == null) {
			return value;
		}
		switch (paramType) {
		case STRING: {
			return String.valueOf(value);
		}
		case DECIMAL: {
			return new BigDecimal(String.valueOf(value));
		}
		case LONG: {
			return Long.valueOf(String.valueOf(value));
		}
		case DATE: {
			return convertToDate(value);
		}
		case INTEGER: {
			return Integer.valueOf(String.valueOf(value));
		}
		case LONG_ARRAY:
		case STRING_ARRAY: {
			return convertToList(value, paramType.javaType);
		}
		default:
			break;
		}
		return value;
	}

	/**
	 * 生成分页(安卓专用)
	 * 
	 * @param beginIndex
	 * @param pageSize
	 */
	public void createLimit(Integer beginIndex, Integer pageSize) {
		if (beginIndex != null)
			setBeginIndex(beginIndex.intValue());
		if (pageSize != null)
			setPageSize(pageSize.intValue());
		this.limitSql.append(" limit " + this.beginIndex + "," + this.pageSize
				+ " ");
	}

	/**
	 * 
	 * 生成查询数量的SQL
	 * 
	 * @return SQL查询数量的语句
	 */
	public String getCountSql() {		
		String selectSql = this.selectSql.toString();
		String whereSql = this.whereSql.toString();
		String orderSql = this.orderSql.toString();
		String groupSql = this.groupSql.toString();
		StringBuilder sql = new StringBuilder();
		if (Util.isNull(selectSql))
			try {
				throw new IitdevException("没有选择SELECT子句");
			} catch (IitdevException e) {
				e.printStackTrace();
			}
		sql.append(selectSql);
		if (StringUtils.isNotBlank(whereSql.toString())) {
			if (!isSelectHasWhere()) {
				sql.append(" where  ");
			}
			sql.append(whereSql.toString());
		}

		if (Util.isNotNull(groupSql) && !isGroupHasGroup(groupSql)) {
			groupSql = " group by " + groupSql;
		}

		if (Util.isNotNull(orderSql) && !isOrderHasOrder(orderSql)) {
			orderSql = " order by " + orderSql;
		}

		if (Util.isNull(groupSql))
			groupSql = "";

		if (Util.isNull(orderSql))
			orderSql = "";

		this.selectSql = new StringBuffer(selectSql);
		this.groupSql = new StringBuffer(groupSql);
		this.whereSql = new StringBuffer(whereSql);
		this.orderSql = new StringBuffer(orderSql);

		sql.append(" ").append(groupSql);
		sql.append(" ").append(orderSql);
		
		
		String sqlstr = sql.toString();
		int iBeginPos = sqlstr.toLowerCase().trim().indexOf("from");
		int iGroupFlag = sqlstr.toLowerCase().trim().indexOf("group by");
		if (iGroupFlag <= 0) {
			sqlstr = "select count(1) " + sqlstr.substring(iBeginPos);
		} else {
			sqlstr = "select sum(ncdcount) from (select ifnull(count(distinct 1),0) as  ncdcount "
					+ sqlstr.substring(iBeginPos) + ") as dCount";
		}
		return sqlstr;
	}

	@SuppressWarnings("rawtypes")
	public Class getQueryClazz() {
		return queryClazz;
	}
	
	@SuppressWarnings("rawtypes")
	public void setQueryClazz(Class queryClazz) {
		this.queryClazz = queryClazz;
	}

	@SuppressWarnings("rawtypes")
	public RowMapper getRowMapper() {
		if (this.rowMapper != null)
			return this.rowMapper;
		if (queryClazz != null) {
			return SpringBeanRowMapper.newInstance(queryClazz);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}
}
