package com.iitdev.ioms.book.data.bo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * BookRecord
 */
@Table(name = "bo_book_record")
public class BookRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	public BookRecord(){}
	//属性
	private Long bookRecordId;//主键ID
	private Long staffId;//借阅人
	private Long bookId;//借阅书籍
	private Date borrowDate;//借阅日期
	private Date returnDate;//归还日期
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_record_id", unique = true, nullable = false,length = 10)
	public Long getBookRecordId() {
		return this.bookRecordId;
	}
	
	public void setBookRecordId(Long BookRecordId) {
		this.bookRecordId = BookRecordId;
	}
	
	@Column(name = "staff_id",unique = false,nullable = false,length = 10)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	@Column(name = "book_id",unique = false,nullable = false,length = 10)
	public Long getBookId() {
		return this.bookId;
	}
	
	public void setBookId(Long value) {
		this.bookId = value;
	}
	
	@Column(name = "borrow_date",unique = false,nullable = false,length = 0)
	public Date getBorrowDate() {
		return this.borrowDate;
	}
	
	public void setBorrowDate(Date value) {
		this.borrowDate = value;
	}
	
	@Column(name = "return_date",unique = false,nullable = true,length = 0)
	public Date getReturnDate() {
		return this.returnDate;
	}
	
	public void setReturnDate(Date value) {
		this.returnDate = value;
	}
	
	
	
}
