package com.iitdev.orm.analysis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;

import com.iitdev.exception.IitdevRuntimeException;
import com.iitdev.globals.AnalysisConstant;
import com.iitdev.orm.xml.Column;
import com.iitdev.utils.Util;

public class AnalysisUtil {
	/**
	 * get database table name
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getTableName(Class<?> clazz) {
		return AnalysisConstant.beanMap.get(clazz).getName();
	}

	/**
	 * get database primary key
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getPrimaryKey(Class<?> clazz) {
		return AnalysisConstant.beanMap.get(clazz).getId().getName();
	}

	/**
	 * get database primary key
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> Serializable getPrimaryKeyValue(T entity) {
		Class<?> clazz = entity.getClass();
		String columnName = AnalysisConstant.beanMap.get(clazz).getId()
				.getColumn();
		Serializable id = null;
		Field field = null;
		try {
			field = clazz.getDeclaredField(Util.getToField(columnName));
			field.setAccessible(true);
			id = (Serializable) field.get(entity);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * get database sql 1.insert colum 2.update colum id
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getExceSQL(Class<?> clazz, UpdateType updateType) {
		List<Column> clumnObjs = AnalysisConstant.beanMap.get(clazz)
				.getColumns();
		StringBuffer sql = new StringBuffer();
		StringBuffer columnBuffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		switch (updateType) {
			case INSERT: {
				columnBuffer.append(" insert into ").append(getTableName(clazz))
						.append(" (");
				valueBuffer.append(" values(");
				for (Column column : clumnObjs) {
					columnBuffer.append(column.getName()).append(",");
					valueBuffer.append("?,");
				}
				columnBuffer.deleteCharAt(columnBuffer.lastIndexOf(","));
				valueBuffer.deleteCharAt(valueBuffer.lastIndexOf(","));
				columnBuffer.append(")");
				valueBuffer.append(")");
			}break;
			case MODIFY: {
				columnBuffer.append("update ").append(getTableName(clazz))
						.append(" set ");
				valueBuffer.append(" where ").append(getPrimaryKey(clazz))
						.append("=?");
				for (Column column : clumnObjs) {
					columnBuffer.append(column.getName()).append("=?,");
				}
				columnBuffer.deleteCharAt(columnBuffer.lastIndexOf(","));
			}break;
		}
		sql.append(columnBuffer).append(valueBuffer);
		return sql.toString();
	}

	public static <T> PreparedStatementSetter getPrestmtSetter(final T entity,
			final UpdateType updateType) {
		final List<Column> clumnObjs = AnalysisConstant.beanMap.get(
				entity.getClass()).getColumns();
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				Field field = null;
				for (int i = 0; i < clumnObjs.size(); i++) {
					try {
						field = entity.getClass().getDeclaredField(Util.getToField(
								clumnObjs.get(i).getColumn()));
						field.setAccessible(true);
						switch (field.getType().getSimpleName().trim()) {
							case "Date": {
								ps.setObject(i+1, (Date)field.get(entity));
							}break;
							case "Long": {
								if(field.get(entity)!=null)
									ps.setLong(i+1, (Long)field.get(entity));
								else
									ps.setObject(i+1, field.get(entity));
							}break;
							case "BigDecimal": {
								ps.setBigDecimal(i+1, (BigDecimal)field.get(entity));
							}break;
							case "String": {
								ps.setString(i+1, (String)field.get(entity));
							}break;
							case "Boolean": {
								ps.setBoolean(i+1, (Boolean)field.get(entity));
							}break;
							default:
								throw new IitdevRuntimeException(
										"未找到匹配类型,请将BO类型为如下[Date、Long、String、BigDecimal、Boolean]");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(updateType==UpdateType.MODIFY){
					ps.setLong(clumnObjs.size()+1, (Long) getPrimaryKeyValue(entity));
				}
			}
		};
	}

	public enum UpdateType {
		INSERT, MODIFY
	}
}
