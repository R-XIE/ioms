package com.iitdev.ioms.operation.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {
	public final static  Map<Long,String> M_LOG_STATE = new LinkedHashMap<Long, String>(){
		/**
		 * 已提交、未提交
		 */
		private static final long serialVersionUID = 1L;

		{
			put(-1l,"未通过");
			put(0l,"测试中");
			put(1l,"已通过");
		}
	};
	
	
	public final static  Map<Long,String> M_PWD_LEVEL = new LinkedHashMap<Long, String>(){
		/**
		 * 公开信息,机密信息
		 */
		private static final long serialVersionUID = 1L;
		{
			put(-1l,"公开信息");
			put(1l,"机密信息");
		}
	};
	
	public final static Map<Long,String> M_BACKUP_STATE =new LinkedHashMap<Long,String>(){

		/**
		 * 备份状态
		 */
		private static final long serialVersionUID = 1L;
		{
			put(-1l,"计划备份");
			put(1l,"备份完成");
		}
		
	};
}