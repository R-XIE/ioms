package com.iitdev.ioms.book.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.book.data.vo.BookRecordVO;
import com.iitdev.ioms.book.data.vo.BookTypeVO;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookRecordBS;
import com.iitdev.ioms.book.service.BookTypeBS;
import com.iitdev.orm.PublicBS;
import com.iitdev.web.SpringGetBean;


public class CmdUtils {

	/**
	 * 获取所有的用户信息
	 * @return
	 */
	public static Map<Long,String> getStaffMap(){
		return com.iitdev.ioms.member.common.CmdUtils.getStaffMap();
	}
	
	
	/**
	 * 获取所有的图书类别
	 * @return
	 */
	public static Map<Long,String> getBookTypeMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		BookTypeBS bookTypeBS = (BookTypeBS) SpringGetBean.getBean("bookTypeBS");
		List<BookTypeVO> bookTypeList= bookTypeBS.queryListAll();
		for (BookTypeVO vo : bookTypeList) {
			map.put(vo.getBookTypeId(), vo.getBookTypeName());
		}
		return map;
	}
	
	/**
	 * 获取所有的图书信息
	 * @return
	 */
	public static Map<Long,String> getBookMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		BookBS bookBS = (BookBS) SpringGetBean.getBean("bookBS");
		List<BookVO> bookList= bookBS.queryListAll();
		for (BookVO vo : bookList) {
			map.put(vo.getBookId(), vo.getBookCode()+":"+vo.getBookName());
		}
		return map;
	}
	
	/**
	 * 获取图书下借出记录
	 * @param bookId
	 * @return
	 */
	public static BookRecordVO getRecordByBook(Long bookId){
		BookRecordBS bookRecordBS=(BookRecordBS)SpringGetBean.getBean("bookRecordBS");
		return bookRecordBS.queryRecordByBook(bookId);
	}
	
	/**
	 * 获取借书用户
	 * @param bookId
	 * @return
	 */
	public static String getBorrowStaff(Long bookId){
		PublicBS publicBS =(PublicBS)SpringGetBean.getBean("publicBS");
		String sql="select staff.staff_real_name from bo_book_record obj  "
				+ " INNER JOIN m_staff staff on obj.staff_id=staff.staff_id "
				+ " WHERE obj.return_date is null and obj.book_id=?";
		return (String) publicBS.queryForObject(sql, new Object[]{bookId});
	}
}
