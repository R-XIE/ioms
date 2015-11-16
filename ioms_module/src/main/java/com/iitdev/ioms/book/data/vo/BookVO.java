package com.iitdev.ioms.book.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.ioms.book.data.bo.Book;

/**
 * Book
 *
 */
public class BookVO implements Serializable {
	private static final long serialVersionUID = 1L;
	// ///////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from bo_book obj";
	private Long bookId;// 主键ID
	private String bookCode;// 图书编号
	private String bookType;// 类型ID
	private String bookName;// 图书名称
	private BigDecimal bookPrice;// 图书作者
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookInputDate;// 图书录入日期
	private Long bookState;// 图书状态｛已借出,在库,丢失｝
	private String bookDesc;// 图书描述
	// /
	private String bookTypeLable;
	private String borrowStaffName;

	// ///////////////////////getter and setter //////////////////////////
	public Long getBookId() {
		return this.bookId;
	}

	public void setBookId(Long value) {
		this.bookId = value;
	}

	public String getBookCode() {
		return this.bookCode;
	}

	public void setBookCode(String value) {
		this.bookCode = value;
	}

	public String getBookType() {
		return this.bookType;
	}

	public void setBookType(String value) {
		this.bookType = value;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String value) {
		this.bookName = value;
	}

	public BigDecimal getBookPrice() {
		return this.bookPrice;
	}

	public void setBookPrice(BigDecimal value) {
		this.bookPrice = value;
	}

	public Date getBookInputDate() {
		return this.bookInputDate;
	}

	public void setBookInputDate(Date value) {
		this.bookInputDate = value;
	}

	public Long getBookState() {
		return this.bookState;
	}

	public void setBookState(Long value) {
		this.bookState = value;
	}

	public String getBookDesc() {
		return this.bookDesc;
	}

	public void setBookDesc(String value) {
		this.bookDesc = value;
	}

	public String getBookTypeLable() {
//		if (bookType != null) {
//			String[] indexArr = this.bookType.split(",");
//			StringBuffer sb = new StringBuffer();
//			for (String str : indexArr) {
//				String value = CmdUtils.getBookTypeMap().get(new Long(str));
//				if (value != null) {
//					sb.append(CmdUtils.getBookTypeMap().get(new Long(str)));
//					sb.append(",");
//				}
//			}
//			sb.deleteCharAt(sb.lastIndexOf(","));
//			this.bookTypeLable = sb.toString();
//			return bookTypeLable;
//		}
//		else
//			return "";
		return this.bookTypeLable;
	}

	public void setBookTypeLable(String bookTypeLable) {
		this.bookTypeLable = bookTypeLable;
	}

	public String getBorrowStaffName() {
//		if (this.bookState.equals(new Long(-1))) {
//			this.borrowStaffName = CmdUtils.getBorrowStaff(this.bookId);
//		} else {
//			this.borrowStaffName = "无";
//		}
//		return borrowStaffName;
		return this.borrowStaffName;
	}

	public void setBorrowStaffName(String borrowStaffName) {
		this.borrowStaffName = borrowStaffName;
	}

	// ////////////////////////////////////////////////////
	public BookVO() {
	}

	public BookVO(Book book) {
		BeanUtils.copyProperties(book, this);
	}

	public void copyValueTo(Book bo) {
		BeanUtils.copyProperties(this, bo);
	}

	public Book cloneBO() {
		Book bo = new Book();
		this.copyValueTo(bo);
		return bo;
	}

	public static List<Book> cloneBOList(List<BookVO> vos) {
		if (vos == null || vos.isEmpty())
			return new ArrayList<Book>(0);
		List<Book> result = new ArrayList<Book>(vos.size());
		for (BookVO vo : vos) {
			Book bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}