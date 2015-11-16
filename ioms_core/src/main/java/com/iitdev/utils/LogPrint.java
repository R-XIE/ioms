package com.iitdev.utils;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * 打印LOG工具类
 * 
 * @author jerry
 * 
 */
public class LogPrint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7144115377076576100L;

	private transient Logger logger = null;

	/**
	 * 构造方法,使用类名进行构造
	 * 
	 * @param className
	 */
	public LogPrint(final String className) {
		logger = Logger.getLogger(className);
		
	}

	/**
	 * 构造方法,使用CLASS对象进行构造
	 * 
	 * @param clazz
	 */
	public LogPrint(final Class<?> clazz) {
		logger = Logger.getLogger(clazz);
	}

	/**
	 * debug级别是否可用
	 * 
	 * @return
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * debug输出信息
	 * 
	 * @param message
	 */
	public void debug(Object message) {
		logger.debug(message);
	}

	/**
	 * debug输出信息
	 * 
	 * @param message
	 * @param t
	 *            异常信息输出
	 */
	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	/**
	 * error输出Log信息
	 * 
	 * @param message
	 */
	public void error(Object message) {
		logger.error(message);
	}

	/**
	 * error输出Log信息
	 * 
	 * @param message
	 * @param t
	 *            异常信息输出
	 */
	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	/**
	 * fatal输出Log信息
	 * 
	 * @param message
	 */
	public void fatal(Object message) {
		logger.fatal(message);
	}

	/**
	 * fatal输出Log信息
	 * 
	 * @param message
	 * @param t
	 *            异常信息输出
	 */
	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	/**
	 * Info级别是否可用
	 * 
	 * @return
	 */
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * info级别输出Log信息
	 * 
	 * @param message
	 */
	public void info(Object message) {
		logger.info(message);
	}

	/**
	 * info级别输出Log信息
	 * 
	 * @param message
	 * @param t
	 *            异常信息输出
	 */
	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	/**
	 * warn级别输出Log信息
	 * 
	 * @param message
	 */
	public void warn(Object message) {
		logger.warn(message);
	}

	/**
	 * warn级别输出Log信息
	 * 
	 * @param message
	 * @param t
	 *            异常信息输出
	 */
	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}
}
