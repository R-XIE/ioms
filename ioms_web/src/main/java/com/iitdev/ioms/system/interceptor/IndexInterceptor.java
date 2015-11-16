package com.iitdev.ioms.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;

/**
 * 判断用户是否登录 没登录的跳转到登录页面 否则调整到主页
 * @author jerry
 *
 */
public class IndexInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if(path.matches(PropertiesConstant.getUrlIndexFilter())){
			return true;
		}else{
			LoginInfo loginInfo = null;
			loginInfo = (LoginInfo) request.getSession().getAttribute(
					PropertiesConstant.getSessionKey());
			if (loginInfo != null) {
				return true;
			}else{
				response.sendRedirect(request.getContextPath() + "/login.htm");
				return false;
			}
		}
	}
}