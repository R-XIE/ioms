package com.iitdev.ioms.book.data.vo;
import com.iitdev.ioms.book.data.bo.BookType;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * BookType
 *
 */
public class BookTypeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from bo_book_type obj" ;
	private Long bookTypeId;//主键ID
	private String bookTypeName;//图书类型名称
	private String bookTypeDesc;//图书类型描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getBookTypeId() {
		return this.bookTypeId;
	}
	public void setBookTypeId(Long value) {
		this.bookTypeId = value;
	}
	public String getBookTypeName() {
		return this.bookTypeName;
	}
	public void setBookTypeName(String value) {
		this.bookTypeName = value;
	}
	public String getBookTypeDesc() {
		return this.bookTypeDesc;
	}
	public void setBookTypeDesc(String value) {
		this.bookTypeDesc = value;
	}
	
	
	//////////////////////////////////////////////////////
	public BookTypeVO(){}

	public BookTypeVO(BookType bookType){
		BeanUtils.copyProperties(bookType,this);
	}
	public void copyValueTo(BookType bo){
		BeanUtils.copyProperties(this,bo);
	}
	public BookType cloneBO(){
		BookType bo = new BookType();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<BookType> cloneBOList(List<BookTypeVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<BookType>(0);
		List<BookType> result = new ArrayList<BookType>(vos.size());
		for(BookTypeVO vo: vos){
			BookType bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}