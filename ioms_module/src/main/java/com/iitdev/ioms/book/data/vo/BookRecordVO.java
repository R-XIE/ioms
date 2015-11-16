package com.iitdev.ioms.book.data.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.ioms.book.data.bo.BookRecord;

/**
 * BookRecord
 *
 */
public class BookRecordVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,book.book_code,book.book_name,book.book_type,staff.staff_real_name from bo_book_record obj  "
			+ "INNER JOIN m_staff staff on staff.staff_id=obj.staff_id "
			+ "INNER JOIN bo_book book on book.book_id=obj.book_id " ;
	private Long bookRecordId;//主键ID
	private Long staffId;//借阅人
	private Long bookId;//借阅书籍
	@DateTimeFormat(pattern="yyyy-MM")
	private Date borrowDate;//借阅日期
	@DateTimeFormat(pattern="yyyy-MM")
	private Date returnDate;//归还日期
	
	private Long bookRecordState;
	//
	private String bookCode;
	private String bookName;
	private String bookType;
	private String staffRealName;
	private String bookTypeLable;
	/////////////////////////getter and setter //////////////////////////
	public Long getBookRecordId() {
		return this.bookRecordId;
	}
	public void setBookRecordId(Long value) {
		this.bookRecordId = value;
	}
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	public Long getBookId() {
		return this.bookId;
	}
	public void setBookId(Long value) {
		this.bookId = value;
	}
	public Date getBorrowDate() {
		return this.borrowDate;
	}
	public void setBorrowDate(Date value) {
		this.borrowDate = value;
	}
	public Date getReturnDate() {
		return this.returnDate;
	}
	public void setReturnDate(Date value) {
		this.returnDate = value;
	}
	
	
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getStaffRealName() {
		return staffRealName;
	}
	public void setStaffRealName(String staffRealName) {
		this.staffRealName = staffRealName;
	}
	public Long getBookRecordState() {
		if(this.returnDate==null)
			this.bookRecordState=0l;
		else this.bookRecordState=1l;
		return bookRecordState;
	}
	public void setBookRecordState(Long bookRecordState) {
		this.bookRecordState = bookRecordState;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	public String getBookTypeLable() {
//		String[]indexArr=this.bookType.split(",");
//		StringBuffer sb =new StringBuffer();
//		for (String str : indexArr) {
//			String value=CmdUtils.getBookTypeMap().get(new Long(str));
//			if(value!=null){
//				sb.append(CmdUtils.getBookTypeMap().get(new Long(str)));
//				sb.append(",");
//			}				
//		}
//		sb.deleteCharAt(sb.lastIndexOf(","));
//		this.bookTypeLable=sb.toString();
		return this.bookTypeLable;
	}
	public void setBookTypeLable(String bookTypeLable) {
		this.bookTypeLable = bookTypeLable;
	}
	//////////////////////////////////////////////////////
	public BookRecordVO(){}

	public BookRecordVO(BookRecord bookRecord){
		BeanUtils.copyProperties(bookRecord,this);
	}
	public void copyValueTo(BookRecord bo){
		BeanUtils.copyProperties(this,bo);
	}
	public BookRecord cloneBO(){
		BookRecord bo = new BookRecord();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<BookRecord> cloneBOList(List<BookRecordVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<BookRecord>(0);
		List<BookRecord> result = new ArrayList<BookRecord>(vos.size());
		for(BookRecordVO vo: vos){
			BookRecord bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}