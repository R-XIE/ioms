package com.iitdev.ioms.book.service;

import java.util.List;
import java.util.Map;

import com.iitdev.ioms.book.data.bo.BookType;
import com.iitdev.ioms.book.data.vo.BookTypeVO;
import com.iitdev.orm.PublicBS;

/**
 *图书类型 SERVICE接口 
 */
public interface BookTypeBS extends PublicBS {
	/**通用查询方法***/
	public BookTypeVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public BookType addBookType(BookType entity) throws Exception;//有外键的字段必须填充,而且要一致
	public BookType modifyBookType(BookType entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public Boolean delBookType(BookType entity)throws Exception;
	public List<BookTypeVO> queryListAll();
	 /**
     * 获取所有的图书类型
     * @return 
     */
    public Map<Long, String> getBookTypeMap();
    /**
     * 获取图书类型的lable
     * @param bookType
     * @return 
     */
    public String getBookTypeLable(String bookType);
}