package com.iitdev.action;

import java.io.Serializable;

import com.iitdev.page.Result;

public class ActionHiddenForm<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final String VIEW = "view";
	public final String INPUT = "input";
	/**
	 * 页面类型 
	 */
	private String func;
	/**
	 * 内置对象:主要是处理分页,默认当前页数为1
	 */
	private Integer currPage = 1;// 当前页码
	/**
	 * 结果集,用于返回分页信息的结果集
	 */
	private Result<T> result;
	
	/**
	 * 传入的实体类
	 */
	private T entity;
	
	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}
	
	public Result<T> getResult() {
		return result;
	}

	public void setResult(Result<T> result) {
		this.result = result;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public T getEntity() {
		return this.entity;
	}
}
