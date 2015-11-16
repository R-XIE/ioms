package com.iitdev.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;

/**
 * Title: springmvc工具类,主要是用来获取servlet的基本信息
 * 
 * @author jerry Create Date:2013-4-27 下午2:00:47
 * @param <T>
 **/

public class BaseAction<T> {
	
	private Class<T> clazzT;
	// -- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	protected final Logger logger;
	/**
	 * 为spring mvc提供的session接口类,可直接使用 exp: HttpSession session = super.session;
	 */
	protected  HttpSession session;
	/**
	 * 由spring mvc提供的servlet中的resquest对应 <code>HttpServletResponse</code>
	 */
	protected  HttpServletResponse response;

	/**
	 * 由spring mvc提供的servlet中的response对应<code>HttpServletRequest</code>
	 */
	protected  HttpServletRequest request;
	/**
	 * 由spring mvc提供的全局变量由服务器的生命周期决定
	 */
	protected  ServletContext application;
	/**
	 * 工程名
	 */
	protected  String appName;
	/**
	 * 返回当前访问IP地址
	 */
	protected  String ipAddress;// 使用者的IP
	/**
	 * 返回当前表单的URL
	 */
	protected  String fromUrl;
	/**
	 * 返回Cookices的所有内容
	 */
	protected  Cookie[] cookies;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.application = session.getServletContext();
		this.appName = request.getContextPath();
		this.ipAddress = request.getRemoteAddr();
		this.fromUrl = request.getHeader("Referer");
		this.cookies = request.getCookies();
	}

	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		clazzT = (Class<T>) type.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(clazzT);
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(), "utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void printExcel(final String fileName, final Workbook wb) {
		try {
			response.setContentType("application/msexcel;charset=UTF-8"); // 两种方法都可以
			response.setHeader("Content-Disposition", "attachment;filename="
					+ java.net.URLEncoder.encode(fileName, "UTF-8"));
			// 客户端不缓存
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 直接输出内容的简便函数. contentType 输出类型 content 内容 nocache 缓存
	 */
	protected void print(final String contentType, final String content,
			final boolean nocache) {
		HttpServletResponse response = initResponseHeader(contentType, nocache);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected void printText(final String text) {
		print(TEXT_TYPE, text, false);
	}

	protected void printHtml(final String html) {
		print(HTML_TYPE, html, false);
	}

	protected void printXml(final String xml) {
		print(XML_TYPE, xml, false);
	}

	protected void printJson(final String json) {
		print(JSON_TYPE, json, false);
	}

	private HttpServletResponse initResponseHeader(final String contentType,
			final boolean noCache) {
		// 分析headers参数
		String encoding = "utf-8";
		HttpServletResponse response = this.response;
		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			// Http 1.1 header
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}

	

	protected LoginInfo getLoginInfo() {
		LoginInfo loginInfo = null;
		loginInfo = (LoginInfo) this.session.getAttribute(PropertiesConstant
				.getSessionKey());
		return loginInfo;
	}

	protected void setLoginInfo(LoginInfo loginInfo) {
		this.session
				.setAttribute(PropertiesConstant.getSessionKey(), loginInfo);
	}

	protected void removeLoginInfo() {
		this.session.removeAttribute(PropertiesConstant.getSessionKey());
	}

	/**
	 * 取得HttpRequest中Parameter的简化方法.
	 */
	protected String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * 设置给request值,key=json,为了在页面输入json信息以便显示在页面上去
	 */
	protected void requestSetJson(String json) {
		request.setAttribute("json", json);
	}
}