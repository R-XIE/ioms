package com.iitdev.ioms.book.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {

	public final static Map<Long, String> M_BOOK_STATE = new LinkedHashMap<Long, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//图书状态｛已借出,在库,丢失｝
		{
			put(0l, "在库");// valid
			put(-1l, "已借出");// valid
			put(-2l, "丢失、破损无法使用");// invalid
		}
	};
	
	
	public final static Map<Long, String> M_BOOK_STATE_UPDATE = new LinkedHashMap<Long, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//图书状态｛已借出,在库,丢失｝
		{
			put(0l, "在库");// valid
			put(-2l, "丢失、破损无法使用");// invalid
		}
	};
	
	public final static Map<Long, String> M_BOOK_RECORD_STATE = new LinkedHashMap<Long, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//图书状态｛已借出,在库,丢失｝
		{
			put(0l, "借阅中");// valid
			put(1l, "已归还");// invalid
		}
	};
	
}
