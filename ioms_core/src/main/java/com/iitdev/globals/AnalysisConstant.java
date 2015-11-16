package com.iitdev.globals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.iitdev.orm.xml.Table;


public class AnalysisConstant {

	
	public static Map<Class<?>,Table> beanMap = new HashMap<Class<?>,Table>();
	
	
	//拦截器使用所有的URL
	public static Map<Long,ResourcesPO> allRes=new LinkedHashMap<Long,ResourcesPO>();
}