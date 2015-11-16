package com.iitdev.ioms.system.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.globals.ResourcesPO;
import com.iitdev.ioms.permiss.service.PermissionsBS;
import com.iitdev.utils.Util;
import com.iitdev.web.SpringGetBean;

/**
 * 判断用户是否有权限访问资源
 * 
 * @author jerry
 *
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(PropertiesConstant.getUrlIndexFilter()) || path.indexOf("/welcome/", 0)==0) {
			return true;
		} else {
			HttpSession session = request.getSession();
			String url = path;
			url = url.substring(url.indexOf('/') + 1, url.indexOf("Action") + 6);
			if (session.getAttribute(PropertiesConstant.getSessionResKey()) == null) {
				List<String> defaultUrl = new ArrayList<String>();
				LoginInfo loginInfo = null;
				loginInfo = (LoginInfo) request.getSession().getAttribute(
						PropertiesConstant.getSessionKey());
				PermissionsBS permissionsBS = (PermissionsBS) SpringGetBean
						.getBean("permissionsBS");
				List<ResourcesPO> resource = permissionsBS
						.permissResourceByStaff(loginInfo.getStaffId());
				for (ResourcesPO resourcesVO : resource) {
					if (resourcesVO.getResourcesId() == 44) {
						// 图书的子菜单
						defaultUrl.add("book/bookRecordActionList.htm");
					}
					defaultUrl.add(resourcesVO.getResourcesUrl());
				}
				session.setAttribute(PropertiesConstant.getSessionResKey(),
						defaultUrl);
				if (!containElement(defaultUrl, url)) {
					response.sendRedirect(request.getContextPath()
							+ "/error/500.jsp");
					return false;
				} else {
					return true;
				}
			} else {
				@SuppressWarnings("unchecked")
				List<String> defaultUrl = (List<String>) session
						.getAttribute(PropertiesConstant.getSessionResKey());
				if (!containElement(defaultUrl, url)) {
					response.sendRedirect(request.getContextPath()
							+ "/error/500.jsp");
					return false;
				} else {
					return true;
				}
			}
		}

	}

	private boolean containElement(List<String> arr, String val) {
		boolean bFlag = false;
		for (String t : arr) {
			if (Util.isNotNull(t) && (t.indexOf(val) != -1)) {
				bFlag = true;
				break;
			}
		}
		return bFlag;
	}
}
