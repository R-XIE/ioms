package com.iitdev.ioms.book.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.book.data.bo.BookType;
import com.iitdev.ioms.book.data.vo.BookTypeVO;
import com.iitdev.ioms.book.service.BookTypeBS;

/**
 * 
 * 图书类型 Action
 * 
 */
@Controller
@RequestMapping("/book")
public class BookTypeAction extends BaseAction<BookType> {
	@Autowired
	private BookTypeBS bookTypeBS;
	
	public BookTypeAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("bookTypeActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("book/bookTypeList");
		List<BookTypeVO> bookTypeList= bookTypeBS.queryListAll();
		view.addObject("list", bookTypeList);
		return view;

	}
	@RequestMapping("bookTypeActionDel")
	public void del(Long recordId) throws Exception {
		BookTypeVO vo = bookTypeBS.queryVOById(recordId);
		boolean res = bookTypeBS.delBookType(vo.cloneBO());
		super.printJson(res + "");
	}
	
	@RequestMapping("bookTypeActionSave")
	public void save(BookType bookType) throws Exception {
		BookType temp;
		if (bookType.getBookTypeId() == null) {
			temp = bookTypeBS.addBookType(bookType);
		}else{
			temp =bookTypeBS.modifyBookType(bookType);
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

}