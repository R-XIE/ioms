package com.iitdev.page;

import java.io.Serializable;

/**
 * @author Jerry
 * @version 1.1
 * 保存的信息有:每页记录数,总记录数,当前页数,起始的Index,是否有上一页和下一页
 */
public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 1 每页显示的数
	private int everyPageNum;
	// 2总记录数
	private int totalCount;
	// 3总页数
	private int totalPage;
	// 4当前页
	private int currPage;
	// 5起始点
	private int beginIndex;
	// 6是否有上一页
	private boolean hasPrePage;
	// 7是否有下一页
	private boolean hasNextPage;

	public Page(int everyPageNum, int totalCount, int totalPage, int currPage,
			int beginIndex, boolean hasPrePage, boolean hasNextPage) {
		super();
		this.everyPageNum = everyPageNum;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currPage = currPage;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}

	public Page() {
	}

	/**
	 * 每页显示的数目
	 * @return  
	 */
	public int getEveryPageNum() {
		return everyPageNum;
	}

	public void setEveryPage(int everyPageNum) {
		this.everyPageNum = everyPageNum;
	}

	/**
	 * 总记录数
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 总页数最少为1
	 * @return
	 */
	public int getTotalPage() {
		if(totalPage==0)
			this.totalPage=1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 当前页
	 * @return
	 */
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	/**
	 * 起始点
	 * @return
	 */
	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	/**
	 * 是否有上页
	 * @return
	 */
	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

}
