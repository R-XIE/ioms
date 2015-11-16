package com.iitdev.exception;

import java.io.Serializable;

/**
 *Title: 自定义异常类<br/>
 *Description: <br/>
 *Copyright (c) 2013 idea Co.,Ltd <br/>
 *All rights reserved<br/>
 *author Jerry <br/>
 *Create Date:2013-1-17 <br/>
 *Modify By:修改人<br/>
 *Modify Date:修改日期<br/>
 *Remark:修改说明<br/>
 **/

public class IitdevException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IitdevException() {
		super();
	}

	/**
	 * function:自定义异常<br/>
	 * remark:
	 * 
	 * @param msg
	 *            自定义消息
	 **/
	public IitdevException(String msg) {
		super(msg);
	}

	/**
	 * function:自定义异常<br/>
	 * remark:
	 * 
	 * @param msg
	 *            自定义消息
	 * @param cause
	 *            异常类
	 **/
	public IitdevException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * function:自定义异常<br/>
	 * remark:
	 * 
	 * @param cause
	 *            异常类
	 **/
	public IitdevException(Throwable cause) {
		super(cause);
	}
}

