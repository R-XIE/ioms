package com.iitdev.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry
 * @version 1.1 返回page信息和结果集
 * @param <T>
 */
public class Result<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Page page;
	private List<T> list;
	public Integer getIndex(){
		return 1;
	}
	public Integer getLast(){
		if(getPage()!=null){
			return getPage().getTotalPage();
		}else{
			return 1;
		}		
	}
	public Integer getNext(){
		if(getPage()!=null && getPage().isHasNextPage()){
			return getPage().getCurrPage()+1;
		}else if(getPage()!=null && !getPage().isHasNextPage()){
			return getPage().getTotalPage();
		}else{
			return 1;
		}	
	}
	public Integer getPre(){
		if(getPage()!=null && getPage().isHasPrePage()){
			return getPage().getCurrPage()-1;
		}else if(getPage()!=null && !getPage().isHasPrePage()){
			return 1;
		}else{
			return 1;
		}	
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

}
