package com.iitdev.ioms.cost.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {
	
	
	public final static Map<Long, String> M_COST_TYPE_TYPE = new LinkedHashMap<Long, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		{
			put(1l, "收入");// valid
			put(0l, "支出");// invalid
		}
	};
	
}
