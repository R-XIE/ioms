package com.iitdev.tags;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;

@SuppressWarnings("serial")
public class LoginInfoValidateTag extends TagSupport {
	
	/**
	 * 
	 */
	private String login_page;
	private String index_page;
	
	@Override
	public int  doStartTag() throws JspException {
		HttpServletResponse response =(HttpServletResponse) this.pageContext.getResponse();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		HttpSession session =request.getSession();
		LoginInfo loginInfo = null;
		loginInfo = (LoginInfo)session.getAttribute(PropertiesConstant.getSessionKey());
		String login_page=getLogin_page();
		try {
			if (loginInfo == null) {
				//login_page
				LoginInfoValidateTag.this.pageContext.getRequest().getRequestDispatcher(login_page).forward(request, response);
			}else{
				//index_page
				LoginInfoValidateTag.this.pageContext.getRequest().getRequestDispatcher(getIndex_page()).forward(request, response);
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getLogin_page() {
		return login_page;
	}

	public void setLogin_page(String login_page) {
		this.login_page = login_page;
	}

	public String getIndex_page() {
		return index_page;
	}

	public void setIndex_page(String index_page) {
		this.index_page = index_page;
	}
}
