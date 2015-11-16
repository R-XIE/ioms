package com.iitdev.orm.xml;

import java.io.Serializable;

import javax.persistence.GenerationType;



public class Id implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GenerationType type;
	private String name;
	private String column;
	/**数据库ID名字**/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**持久化类的字段名**/
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	/**自增类型**/
	public GenerationType getType() {
		return type;
	}
	public void setType(GenerationType type) {
		this.type = type;
	}
	
	
}
