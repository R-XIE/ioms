package com.iitdev.ioms.weekly.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {
	public enum Mweekly{
		SUBMITED,UNSUBMITED
	};
	public final static Long L_WEEKLY_STATE_SUBMIT=0L;
	public final static Long L_WEEKLY_STATE_UNSUBMIT=1L;
	public final static  Map<Long,String> M_WEEKLY_STATE = new LinkedHashMap<Long, String>(){
		/**
		 * 已提交、未提交
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0l,"已提交");
			put(1l,"未提交");
		}
	};

}
