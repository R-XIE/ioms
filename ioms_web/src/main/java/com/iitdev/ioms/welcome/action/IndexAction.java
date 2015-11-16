package com.iitdev.ioms.welcome.action;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.ioms.member.service.StaffBS;

/**
 * 
 * staff Action
 * 
 */
@Controller
public class IndexAction extends BaseAction<Integer> {
	@Autowired
	private StaffBS staffBS;
	public IndexAction() {
		super();
	}
	/************************************** 页面跳转 ******************************/
	////////////验证login///////
	@RequestMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	
	@RequestMapping(value="/login_ajax",method=RequestMethod.POST)
	public void validateLogin(String name,String pwd){
		String msg="用户名与密码不匹配,请重新输入.";
		LoginInfo loginInfo=getLoginInfo();
		loginInfo=staffBS.validatePwd(name.trim(), pwd.trim());
		if(loginInfo==null){
			super.logger.info(msg);
			printHtml("error");
		}else{
			super.setLoginInfo(loginInfo);
			printHtml("success");
		}		
	}
	@RequestMapping("/loginout")
	public ModelAndView  loginOut() throws IOException{
		super.removeLoginInfo();
		super.session.removeAttribute(PropertiesConstant.getSessionResKey());
		response.sendRedirect(super.request.getContextPath()+"/index.jsp");
		return null;
	}

}