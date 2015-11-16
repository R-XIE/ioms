package com.iitdev.ioms.book.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.book.data.bo.BookRecord;
import com.iitdev.ioms.book.data.vo.BookRecordVO;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookRecordBS;
import com.iitdev.ioms.book.service.BookTypeBS;
import com.iitdev.orm.PublicBSImpl;

/**
 * 图书借还记录 SERVICE接口 实现类
 *
 */
@Service("bookRecordBS")
public class BookRecordBSImpl extends PublicBSImpl implements BookRecordBS {
	@Autowired
	private BookBS bookBS;
	@Autowired
	private BookTypeBS bookTypeBS;
	public final String SQL_QUERY_VO = BookRecordVO.QUERY_SQL;

	public BookRecordVO queryVOById(Long id) {
		String sql = SQL_QUERY_VO + " and obj.book_record_id  = ? ";
		return super.queryForBean(BookRecordVO.class, sql, new Object[] { id });
	}
	
	
	public List<BookRecordVO> queryBookRecordByStaff(Long staffId) {
		String sql = SQL_QUERY_VO + " and obj.staff_id  = ? ";
		List<BookRecordVO> recordList = super.queryForBeanList(BookRecordVO.class, sql, new Object[]{staffId});
        return getList(recordList);
	}


	@Override
	public Integer queryCountByStaff(Long staffId) {
		String sql = "select count(1) from bo_book_record obj where obj.staff_id=?";
		return super.queryForInt(sql, new Object[] { staffId });
	}

	@Override
	public BookRecordVO queryRecordByBook(Long bookId) {
		String sql = SQL_QUERY_VO
				+ " where obj.book_id  = ? and obj.return_date is null";
		return super.queryForBean(BookRecordVO.class, sql,
				new Object[] { bookId });
	}

	/** 有外键的字段必须填充,而且要一致 ***/
	public BookRecord addBookRecord(BookRecord entity) throws Exception {
		// 1主表验证和此表的外键字段一致
		// 添加code编码
		// 保存表
		super.saveObject(entity);
		return entity;
	}

	public BookRecord modifyBookRecord(BookRecord entity) throws Exception {
		// 1主表验证和此表的外键字段一致

		// 修改表
		super.updateObject(entity);
		return entity;
	}

	public boolean delBookRecord(BookRecord entity) throws Exception {
		// 1表删除
		if (entity.getReturnDate() != null) {
			delete(entity);
			return true;
		} else {
			return false;
		}

	}

	public boolean modifyReturn(Long recordId) throws Exception {
		// 还书
		BookRecordVO vo = this.queryVOById(recordId);
		vo.setReturnDate(new Date());
		BookRecord temp = this.modifyBookRecord(vo.cloneBO());
		BookVO book = bookBS.queryVOById(temp.getBookId());
		book.setBookState(0l);
		bookBS.modifyBook(book.cloneBO());
		return true;
	}

	public boolean modifyBorrow(BookRecordVO record) throws Exception {
		// jie su
		record.setBorrowDate(new Date());
		record.setReturnDate(null);
		BookRecord temp = addBookRecord(record.cloneBO());
		BookVO book = bookBS.queryVOById(temp.getBookId());
		book.setBookState(-1l);
		bookBS.modifyBook(book.cloneBO());
		return true;
	}


	@Override
	public String getBorrowStaff(Long bookId) {
		String sql = "select staff.staff_real_name from bo_book_record obj  "
				+ " INNER JOIN m_staff staff on obj.staff_id=staff.staff_id "
				+ " WHERE obj.return_date is null and obj.book_id=?";
		return (String) super.queryForObject(sql, new Object[] { bookId });
	}

	@Override
	public List<BookRecordVO> queryVOAllList() {
		List<BookRecordVO> recordList = super.queryForBeanList(BookRecordVO.class, BookRecordVO.QUERY_SQL, new Object[]{});
        return getList(recordList);
	}

	@Override
	public List<BookRecordVO> queryVOAllListByBook(Long bookId) {
		List<BookRecordVO> recordList = super.queryForBeanList(BookRecordVO.class, BookRecordVO.QUERY_SQL + " where obj.book_id=?", new Object[]{bookId});
        return getList(recordList);
	}
	
	/**
     * 更改返回的list,为list添加属性
     *
     * @param list
     * @return
     */
    private List<BookRecordVO> getList(List<BookRecordVO> list) {
        for (int i = 0; i < list.size(); i++) {
            BookRecordVO bookRecordVO = list.get(i);
            String lable = bookTypeBS.getBookTypeLable(bookRecordVO.getBookType());
            bookRecordVO.setBookTypeLable(lable);
            list.set(i, bookRecordVO);
        }
        return list;
    }
}