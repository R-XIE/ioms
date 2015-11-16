package com.iitdev.ioms.system.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;

/**
 * 防止用户二次登录,若登录后输入登录网址则跳转到主页
 * @author jerry
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LoginInfo loginInfo = null;
		loginInfo = (LoginInfo) request.getSession().getAttribute(
				PropertiesConstant.getSessionKey());
		if (loginInfo != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}else{
			return true;
		}
	}
}