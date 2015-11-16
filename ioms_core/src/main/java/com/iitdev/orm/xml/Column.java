package com.iitdev.orm.xml;

import java.io.Serializable;

/**
 * 
 * @author cuisongliu
 *
 */
public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2573742883625396468L;
	private String name;
	private String column;
	private boolean unique;
	private boolean nullable;
	private int length;
	/**对应数据库的名字**/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**是否唯一**/
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	/**是否为空**/
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	/**长度**/
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	/**映射实体字段**/
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
}
