package com.iitdev.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.iitdev.globals.PropertiesConstant;
import com.iitdev.page.Result;

/**
 * remark： 分页服务组件 
 * @author Jerry
 * @version 1.0
 */
public class PageTag extends SimpleTagSupport {
	private Result<?> result;
	@Override
	public void doTag() throws JspException, IOException {
		PageBS pageBS =null;
		 Writer out = getJspContext().getOut();
		try {
			Class<?>  cls= Class.forName(PropertiesConstant.getPageImplClass());
			pageBS= (PageBS) cls.newInstance();
			out.write(pageBS.getHtmlBuilder(result));
		} catch (Exception e) {
			throw new RuntimeException("Construct <pageTags:button /> error:"+e.getMessage());
		}
		super.doTag();
	}
	public Result<?> getResult() {
		return result;
	}
	public void setResult(Result<?> result) {
		this.result = result;
	}
}
