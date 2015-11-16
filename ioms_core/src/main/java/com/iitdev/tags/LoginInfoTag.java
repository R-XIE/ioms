package com.iitdev.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



import com.iitdev.data.LoginInfo;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.utils.Util;

@SuppressWarnings("serial")
public class LoginInfoTag extends TagSupport  {

	/**
	 * 
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int  doStartTag() throws JspException {
		Writer out = this.pageContext.getOut();
		HttpSession session = ((HttpServletRequest)LoginInfoTag.this.pageContext.getRequest()).getSession();
		String outputStr = null;
		LoginInfo loginInfo = (LoginInfo) session
				.getAttribute(PropertiesConstant.getSessionKey());
		switch (type) {
			case "staffId": {
				outputStr = loginInfo.getStaffId().toString();
				break;
			}
			case "staffCode": {
				outputStr = loginInfo.getStaffCode();
				break;
			}
			case "staffLoginName": {
				outputStr = loginInfo.getStaffLoginName();
				break;
			}
			case "staffRealName": {
				outputStr = loginInfo.getStaffRealName();
				break;
			}
			case "staffIcon": {
				if(Util.isNull(loginInfo.getStaffIcon())){
					if(loginInfo.getStaffSex().equals(0l))
						outputStr="icon/default.jpg";
					else
						outputStr="icon/defalut_sales.jpg";
				}else{
					outputStr="welcome/saveImgProfileDownload.htm?fileName="+loginInfo.getStaffIcon();
				}
				break;
			}
		}
		try {
			out.write(outputStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
