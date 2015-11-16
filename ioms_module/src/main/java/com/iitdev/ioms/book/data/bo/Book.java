package com.iitdev.ioms.book.data.bo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Book
 */
@Table(name = "bo_book")
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	public Book(){}
	//属性
	private Long bookId;//主键ID
	private String bookCode;//图书编号
	private String bookType;//类型ID
	private String bookName;//图书名称
	private BigDecimal bookPrice;
	private Date bookInputDate;//图书录入日期
	private Long bookState;//图书状态｛已借出,在库,丢失｝
	private String bookDesc;//图书描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false,length = 10)
	public Long getBookId() {
		return this.bookId;
	}
	
	public void setBookId(Long BookId) {
		this.bookId = BookId;
	}
	
	@Column(name = "book_code",unique = false,nullable = false,length = 50)
	public String getBookCode() {
		return this.bookCode;
	}
	
	public void setBookCode(String value) {
		this.bookCode = value;
	}
	
	@Column(name = "book_type",unique = false,nullable = true,length = 10)
	public String getBookType() {
		return this.bookType;
	}
	
	public void setBookType(String value) {
		this.bookType = value;
	}
	
	@Column(name = "book_name",unique = false,nullable = false,length = 50)
	public String getBookName() {
		return this.bookName;
	}
	
	public void setBookName(String value) {
		this.bookName = value;
	}
	
	@Column(name = "book_price",unique = false,nullable = false,length = 10)
	public BigDecimal getBookPrice() {
		return this.bookPrice;
	}
	
	public void setBookPrice(BigDecimal value) {
		this.bookPrice = value;
	}
	
	@Column(name = "book_input_date",unique = false,nullable = false,length = 0)
	public Date getBookInputDate() {
		return this.bookInputDate;
	}
	
	public void setBookInputDate(Date value) {
		this.bookInputDate = value;
	}
	
	@Column(name = "book_state",unique = false,nullable = false,length = 10)
	public Long getBookState() {
		return this.bookState;
	}
	
	public void setBookState(Long value) {
		this.bookState = value;
	}
	
	@Column(name = "book_desc",unique = false,nullable = true,length = 200)
	public String getBookDesc() {
		return this.bookDesc;
	}
	
	public void setBookDesc(String value) {
		this.bookDesc = value;
	}
	
	
	
}
