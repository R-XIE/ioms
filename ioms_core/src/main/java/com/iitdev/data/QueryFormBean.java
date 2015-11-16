package com.iitdev.data;

import java.io.Serializable;

public class QueryFormBean implements Serializable{
	/**
	 * P:当前页数(默认为1)
	 * S：一页显示多少记录（默认为5）
	 */
	private static final long serialVersionUID = 1L;
	private Integer p;
	private Integer s;
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public Integer getS() {
		return s;
	}
	public void setS(Integer s) {
		this.s = s;
	}
	
}
