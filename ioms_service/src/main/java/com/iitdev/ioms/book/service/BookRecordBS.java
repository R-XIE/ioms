package com.iitdev.ioms.book.service;

//import com.iitdev.ioms.book.data.bo.BookRecord;
import java.util.List;

import com.iitdev.ioms.book.common.CmdUtils;
import com.iitdev.ioms.book.data.bo.BookRecord;
import com.iitdev.ioms.book.data.vo.BookRecordVO;
import com.iitdev.orm.PublicBS;

/**
 *图书借还记录 SERVICE接口 
 */
public interface BookRecordBS extends PublicBS {
	/**通用查询方法***/
	public BookRecordVO queryVOById(Long id);	
	public List<BookRecordVO> queryVOAllList();
	/**
	 * 通过图书获取该图书下所有的借书记录
	 * @see BookRecordAction#bookRecordList(Long)
	 * @param bookId
	 * @return
	 */
	public List<BookRecordVO> queryVOAllListByBook(Long bookId);
	/**通用持久化方法(添加和修改)***/
	public BookRecord addBookRecord(BookRecord entity) throws Exception;//有外键的字段必须填充,而且要一致
	public BookRecord modifyBookRecord(BookRecord entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delBookRecord(BookRecord entity)throws Exception;
	/**
	 * 通过图书获取该图书下所有的借出记录
	 * @see CmdUtils#getRecordByBook(Long)
	 * @param bookId
	 * @return
	 */
	public BookRecordVO queryRecordByBook(Long bookId);
	
	/**
	 * 还书
	 * @see BookRecordAction#returnA(Long)
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	public boolean modifyReturn(Long recordId) throws Exception;
	
	/**
	 * 借书
	 * @see BookRecordAction#save(BookRecordVO)
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean modifyBorrow(BookRecordVO record) throws Exception;
	/**
	 * 登录用户所有借书记录的数量
	 * @see MainAction#main()
	 * @param staffId
	 * @return
	 */
	public Integer queryCountByStaff(Long staffId);
	/**
	 * 登录用户所有的借书记录
	 * @see ModuleAction#bookRecordList()
	 * @param staffId
	 * @return
	 */
	public List<BookRecordVO> queryBookRecordByStaff(Long staffId);
	  /**
     * 获取借书用户
     * @param bookId
     * @return 
     */
    public  String getBorrowStaff(Long bookId);
}

