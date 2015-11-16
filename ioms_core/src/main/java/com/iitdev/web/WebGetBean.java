package com.iitdev.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *Title: 根据bean名字取得相应的Spring Bean <br/>
 *Description: <br/>
 *All Rights Reserved<br/>
 *author Jerry <br/>
 *Create Date:2011-05-11 <br/>
 *Modify By:修改人 <br/>
 *Modify Date:修改日期 <br/>
 *Remark:修改说明<br/>
 *效率低下
 **/
@Deprecated
public final class WebGetBean extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7352741970701313054L;
	private static ApplicationContext appCtx = null; 

	/**
	 * function:servle初始化,取得web应用上下文<br/>
	 * remark:
	 * 
	 * @param servletConfig
	 *            servlet配置
	 **/
	public void init(ServletConfig servletConfig) throws ServletException {
		appCtx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletConfig
						.getServletContext());
	}

	/**
	 * function:根据spring的bean名取得bean对象<br/>
	 * remark:
	 * 
	 * @param beanName
	 *            bean名字
	 * @return bean对象
	 **/
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}

	public void destroy() {
		super.destroy();
	}
	
	public static void setApplicationContext(ApplicationContext ctx){
		appCtx = ctx;
	}
	public static ApplicationContext getApplicationContext(){
		return appCtx;
	}
}