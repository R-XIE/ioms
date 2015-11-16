package com.iitdev.orm.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8052015597717310441L;
	
	private String name; /**对应数据库表名**/
	private Class<?> clazz; /**对应类名**/
	private Id id; //id
	private List<Column> columns=new ArrayList<Column>(); //属性字段
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
