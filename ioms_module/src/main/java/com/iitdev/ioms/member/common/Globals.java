package com.iitdev.ioms.member.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {

	
	public final static Map<Long,String> M_MEMBER_SEX = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0l, "男");//valid
			put(-1l, "女");//invalid
		}
	};
	
	public final static Map<Long,String> M_MEMBER_STATE = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1l, "正式员工");//valid
			put(0l, "试用员工");//valid
			put(-1l, "离职员工");//invalid
		}
	};
	
	public final static Map<Long,String> M_MEMBER_STATE_UNLEAVE = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1l, "正式员工");//valid
			put(0l, "试用员工");//valid
		}
	};
	
	public final static  Map<Long,String> M_MEMBER_DEGREE = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0l,"高中");
			put(1l,"本科");
			put(2l,"专科");
			put(3l,"硕士");
			put(4l,"博士");
		}
	};
	
	public final static  Map<Long,String> M_INTERVIEW_CURRENT = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0l,"在职");
			put(1l,"离职");
		}
	};
	
	//初试通过，复试通过，初试不通过、复试不通过
	public final static  Map<Long,String> M_INTERVIEW_RESULT = new LinkedHashMap<Long, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0l,"初试通过");
			put(1l,"初试不通过");
			put(2l,"复试通过");
			put(3l,"复试不通过");
		}
	};
	
	public final static  Map<Long,String> M_LAEAVE_TYPE = new LinkedHashMap<Long, String>(){
		/**
		 * 事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1l,"事假");
			put(2l,"病假");
			put(3l,"婚假");
			put(4l,"丧假");
			put(5l,"年假");
			put(6l,"工伤");
			put(7l,"产假");
			put(8l,"护理假");
			put(9l,"其他福利假");
		}
	};
	
	public final static  Map<Long,String> M_ALTER_CAUSE = new LinkedHashMap<Long, String>(){
		/**
		 * 调薪、调岗、调职、转正
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1l,"调薪");
			put(2l,"调岗");
			put(3l,"调职");
			put(4l,"转正");
		}
	};
}
