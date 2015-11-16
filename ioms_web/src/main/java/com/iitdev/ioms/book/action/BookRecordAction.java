package com.iitdev.ioms.book.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.book.common.CmdUtils;
import com.iitdev.ioms.book.common.Globals;
import com.iitdev.ioms.book.data.vo.BookRecordVO;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookRecordBS;
import com.iitdev.ioms.permiss.data.bo.Role;

/**
 * 
 * 图书 Action
 * 
 */
@Controller
@RequestMapping("/book")
public class BookRecordAction extends BaseAction<Role> {
	@Autowired
	private BookRecordBS bookRecordBS;
	@Autowired
	private BookBS bookBS;
	public BookRecordAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	/**
	 * 获取所有的图书借书记录
	 * @param t
	 * @return
	 */
	@RequestMapping("bookRecordActionList")
	public ModelAndView list(String t) {
		ModelAndView view = new ModelAndView("book/bookRecordList");
		List<BookRecordVO> recordList= bookRecordBS.queryVOAllList();
		view.addObject("list", recordList);
		view=setGlobals(view);
		return view;
	}
	
	/**
	 * 获取图书的借书记录
	 * @param bookId
	 * @return
	 */
	@RequestMapping("bookRecordActionBookRecordList")
	public ModelAndView bookRecordList(Long bookId) {
		ModelAndView view = new ModelAndView("book/bookRecordList");
		List<BookRecordVO> recordList= bookRecordBS.queryVOAllListByBook(bookId);
		BookVO vo = bookBS.queryVOById(bookId);
		view.addObject("list", recordList);
		view.addObject("code", vo.getBookCode());
		view.addObject("type", bookId);
		view.addObject("bookType", vo.getBookState());
		view=setGlobals(view);
		return view;

	}
	@RequestMapping("bookRecordActionDel")
	public void del(Long recordId) throws Exception {
		BookRecordVO vo = bookRecordBS.queryVOById(recordId);
		boolean res = bookRecordBS.delBookRecord(vo.cloneBO());
		super.printJson(res+"");
	}
	
	@RequestMapping("bookRecordActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("book/bookRecordInput");
		String type="";
		if(recordId!=null){
			BookRecordVO vo = bookRecordBS.queryVOById(recordId);
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
	
	@RequestMapping("bookRecordActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("book/bookRecordInput");
		String type="";
		if(recordId!=null){
			BookRecordVO vo = bookRecordBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("bookRecordActionSave")
	public void save(BookRecordVO record) throws Exception {
		if (record.getBookRecordId() == null) {
			//借书
			bookRecordBS.modifyBorrow(record);
		}else{
			//还书
			bookRecordBS.modifyReturn(record.getBookRecordId());
		}
		super.printJson(true + "");
	}
	/**
	 * 借书信息填写
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bookRecordActionBorrowInput")
	public ModelAndView borrowInput(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("book/bookBorrowInput");
		view=setGlobals(view);
		view.addObject("bookId", recordId);
		return view;
	}
	/**
	 * 还书验证以及还书操作
	 * @param recordId
	 * @throws Exception
	 */
	@RequestMapping("bookRecordActionReturn")
	public void returnA(Long recordId) throws Exception{
		 BookRecordVO vo = CmdUtils.getRecordByBook(recordId);
		 if(vo==null){
			 super.printJson(false + "");
		 }else{
			 bookRecordBS.modifyReturn(vo.getBookRecordId());
			 super.printJson(true + "");
		 }
	}
	
	public static ModelAndView setGlobals(ModelAndView model) {
		Map<Long,String> bookRecordMap=Globals.M_BOOK_RECORD_STATE;
		Map<Long,String> staffMap=CmdUtils.getStaffMap();
		Map<Long,String> bookMap=CmdUtils.getBookMap();
		model.addObject("staffMap", staffMap);
		model.addObject("bookMap", bookMap);
		model.addObject("bookRecordMap", bookRecordMap);
		return model;
	}
}