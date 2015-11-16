package com.iitdev.ioms.book.service;

//import com.iitdev.ioms.book.data.bo.Book;
import java.util.List;

import com.iitdev.ioms.book.common.CmdUtils;
import com.iitdev.ioms.book.data.bo.Book;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.orm.PublicBS;

/**
 *图书 SERVICE接口 
 */
public interface BookBS extends PublicBS {
	/**通用查询方法***/
	public BookVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Book addBook(Book entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Book modifyBook(Book entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public Boolean delBook(Book entity)throws Exception;
	
	/**
	 * 获取可以借的图书数据
	 * @see CmdUtils#getBookMap()
	 * @return
	 */
	public List<BookVO> queryListAll();//用于map的可用数据则未被借出的书
	
	/**
	 * 所有的图书列表
	 * @see BookAction#list(String)
	 * @return
	 */
	public List<BookVO> queryVOListAll();//所有的图书
	
	/**
	 * 获取所有的借出或者在库的图书
	 * @see ModuleAction#bookList()
	 * @return
	 */
	public List<BookVO> queryVOListAllExist();//已经借出或者在库的图书
	
	/**
	 * 获取所有的图书数量
	 * @see MainAction#main()
	 * @return
	 */
	public int queryCountAll();
	
	/**
	 * 验证某类下所有的图书
	 * @param typeId
	 * @return
	 */
	public int validateCountByType(Long typeId);
}

