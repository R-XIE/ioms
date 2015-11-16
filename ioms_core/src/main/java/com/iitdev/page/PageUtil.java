package com.iitdev.page;

/**
 * @author Jerry
 * @version 1.1 page工具类,公有的方法只有生成一个<code>Page</code>信息类,然后获取信息从<code>Page</code>
 *          中获取 createPage是生成的<code>Page</code>的工具类
 */
public class PageUtil {
	private static PageUtil instance = null;
	private static int everyPageNum;

	private PageUtil(int everyPageNum) {
		if (everyPageNum == 0) {
			// 载入默认参数
			everyPageNum = 10;
		}
		PageUtil.everyPageNum = everyPageNum;
	}

	/**
	 * @param everyPageNum 每页的记录数目
	 * @return
	 */
	public static PageUtil getInstance(int everyPageNum) {
		instance = new PageUtil(everyPageNum);
		return instance;
	}


	/**
	 * @return 获取当前页
	 */
	private int getCurrPage(int CurrPage) {
		return CurrPage == 0 ? 1 : CurrPage;
	}

	/**
	 * @return 获取总页数
	 */
	private int getTotalPage(int everyPage, int totalCount) {
		return totalCount % everyPage == 0 ? totalCount / everyPage
				: totalCount / everyPage + 1;
	}

	/**
	 * @return 设置起点
	 */
	private int getBeginIndex(int currPage, int everyPage) {
		return (currPage - 1) * everyPage;
	}

	/**
	 * @return 是否有上一页
	 */
	private boolean getHasPrePage(int currPage) {
		return currPage == 1 ? false : true;
	}

	/**
	 * @return 是否有下一页
	 */
	private boolean getHasNextPage(int currPage, int totalPage) {
		return totalPage == currPage || totalPage == 0 ? false : true;
	}

	/**
	 * @param totalCount
	 *            总记录数
	 * @param currPage
	 *            当前页数
	 * @return page信息类<code>Page</code>
	 */
	public Page createPage(int totalCount, int currPage) {
		int totalPage = getTotalPage(everyPageNum, totalCount);
		if (currPage <= totalPage)
			currPage = getCurrPage(currPage);
		else
			currPage = 1;
		int beginIndex = getBeginIndex(currPage, everyPageNum);
		boolean hasPrePage = getHasPrePage(currPage);
		boolean hasNextPage = getHasNextPage(currPage, totalPage);
		return new Page(getEveryPageNum(), totalCount, totalPage, currPage,
				beginIndex, hasPrePage, hasNextPage);
	}

	/**
	 * 每页的记录数目
	 * @return
	 */
	public int getEveryPageNum() {
		return everyPageNum;
	}

	public void setEveryPageNum(int everyPageNum) {
		PageUtil.everyPageNum = everyPageNum;
	}
}
