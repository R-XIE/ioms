package com.iitdev.ioms.view.data.po;

import java.io.Serializable;
import java.util.Map;


public class JqueryDataRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer iDisplayStart;//起始序列 从0开始
	private Integer iDisplayLength;//每页显示数量
	private Integer iColumns;//显示列数
	private String sSearch;//搜索框的内容
	private Boolean bEscapeRegex;//忽略大小写
	private Integer iSortingCols;//排序列数
	private Integer sEcho;//请求的次数
	private String viewSql;
	private String viewCountSql;
	private Map<String,?> whereFliter;
	public Integer getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public Integer getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public Integer getiColumns() {
		return iColumns;
	}
	public void setiColumns(Integer iColumns) {
		this.iColumns = iColumns;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public Boolean getbEscapeRegex() {
		return bEscapeRegex;
	}
	public void setbEscapeRegex(Boolean bEscapeRegex) {
		this.bEscapeRegex = bEscapeRegex;
	}
	public Integer getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(Integer iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public Integer getsEcho() {
		return sEcho;
	}
	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}
	public String getViewSql() {
		return viewSql;
	}
	public void setViewSql(String viewSql) {
		this.viewSql = viewSql;
	}
	public String getViewCountSql() {
		return viewCountSql;
	}
	public void setViewCountSql(String viewCountSql) {
		this.viewCountSql = viewCountSql;
	}
	public Map<String,?> getWhereFliter() {
		return whereFliter;
	}
	public void setWhereFliter(Map<String,?> whereFliter) {
		this.whereFliter = whereFliter;
	}
	
}
