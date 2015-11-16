package com.iitdev.ioms.base.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {

	
	public final static Map<Long, String> M_PAGE_MAP = new LinkedHashMap<Long, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		{
			put(5l, "5行");// valid
			put(10l, "10行");// invalid
			put(25l, "25行");// valid
			put(50l, "50行");// invalid
			put(100l, "100行");// invalid
			
		}
	};
}
