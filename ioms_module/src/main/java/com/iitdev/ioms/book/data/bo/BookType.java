package com.iitdev.ioms.book.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * BookType
 */
@Table(name = "bo_book_type")
public class BookType implements Serializable{
	private static final long serialVersionUID = 1L;
	public BookType(){}
	//属性
	private Long bookTypeId;//主键ID
	private String bookTypeName;//图书类型名称
	private String bookTypeDesc;//图书类型描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_type_id", unique = true, nullable = false,length = 10)
	public Long getBookTypeId() {
		return this.bookTypeId;
	}
	
	public void setBookTypeId(Long BookTypeId) {
		this.bookTypeId = BookTypeId;
	}
	
	@Column(name = "book_type_name",unique = false,nullable = false,length = 500)
	public String getBookTypeName() {
		return this.bookTypeName;
	}
	
	public void setBookTypeName(String value) {
		this.bookTypeName = value;
	}
	
	@Column(name = "book_type_desc",unique = false,nullable = true,length = 200)
	public String getBookTypeDesc() {
		return this.bookTypeDesc;
	}
	
	public void setBookTypeDesc(String value) {
		this.bookTypeDesc = value;
	}
	
	
	
}
