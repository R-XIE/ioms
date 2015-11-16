package com.iitdev.ioms.book.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.book.common.CmdUtils;
import com.iitdev.ioms.book.common.Globals;
import com.iitdev.ioms.book.data.bo.Book;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.book.service.BookBS;

/**
 * 
 * 图书 Action
 * 
 */
@Controller
@RequestMapping("/book")
public class BookAction extends BaseAction<Book> {
	@Autowired
	private BookBS bookBS;
	
	public BookAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("bookActionList")
	public ModelAndView list(String t) {
		ModelAndView view = new ModelAndView("book/bookList");
		List<BookVO> BookList= bookBS.queryVOListAll();
		view.addObject("list", BookList);
		view=setGlobals(view);
		return view;

	}
	@RequestMapping("bookActionDel")
	public void del(Long recordId) throws Exception {
		BookVO vo = bookBS.queryVOById(recordId);
		boolean res = bookBS.delBook(vo.cloneBO());
		super.printJson(res+"");
	}
	
	@RequestMapping("bookActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("book/bookInput");
		String type="";
		if(recordId!=null){
			BookVO vo = bookBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("bookActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("book/bookInput");
		String type="";
		if(recordId!=null){
			BookVO vo = bookBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("bookActionSave")
	public void save(BookVO book) throws Exception {
		Book temp;
		if (book.getBookId() == null) {
			book.setBookInputDate(new Date());
			temp =bookBS.addBook(book.cloneBO());
		}else{
			BookVO vo = bookBS.queryVOById(book.getBookId());
			book.setBookInputDate(vo.getBookInputDate());
			temp =bookBS.modifyBook(book.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public static ModelAndView setGlobals(ModelAndView model) {
		Map<Long, String> bookMap =Globals.M_BOOK_STATE;
		Map<Long, String> bookTypeMap =CmdUtils.getBookTypeMap();
		Map<Long, String> bookUpdateMap =Globals.M_BOOK_STATE_UPDATE;
		model.addObject("bookMap", bookMap);
		model.addObject("bookTypeMap", bookTypeMap);
		model.addObject("bookUpdateMap", bookUpdateMap);
		return model;
	}
}