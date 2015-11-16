package com.iitdev.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class PermissTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
//		HttpServletRequest request = (HttpServletRequest) this.pageContext
//				.getRequest();
//		HttpSession session = request.getSession();
//			List<ResourcesVO> reslist = (List<ResourcesVO>) session
//					.getAttribute(PropertiesConstant.getSessionResKey());
//			for (ResourcesVO resourcesVO : reslist) {
//				
//			}
		
		return SKIP_BODY;
	}
}
