package com.iitdev.ioms.system.tag;
import com.iitdev.page.Page;
import com.iitdev.page.Result;
import com.iitdev.tags.PageBS;


public class PageBSImpl implements PageBS {

	@Override
	public String getHtmlBuilder(Result<?> result) {
		Page page=result.getPage();
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='row-fluid'><div class='span6'><div class='dataTables_info'>");
		if(page.getTotalCount()!=0)
			sb.append("从"+(page.getBeginIndex()+1)+"到"+(page.getBeginIndex()+result.getList().size())+"条记录,总记录数为"+page.getTotalCount()+"条");
		if(page.getTotalCount()==0)
			sb.append("总记录数为0条");
		sb.append("</div></div><div class='span6'><div id=\"page\" style=\"cursor: pointer;\" class=\"dataTables_paginate  paging_bootstrap pagination\" ");
		sb.append(" currentPage=\""+page.getCurrPage()+"\" ").append(" totalPages=\""+page.getTotalPage()+"\" ");
		sb.append("  numberOfPages=\"5\" ></div></div></div>");
		return sb.toString();
	}

}
