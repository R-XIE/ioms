package com.iitdev.globals;

import java.io.File;

public class PropertiesConstant {
	private static final String CONSTANT_PAGE_SIZE = "jdbc.pageSize";
	private static final String CONSTANT_URL_INDEX_FILTER = "iitdev.url_index_filter";
	private static final String CONSTANT_BIU_PROJECT = "iitdev.biu_project";
	private static final String CONSTANT_BIU_LOCATION = "iitdev.biu_location";
	private static final String CONSTANT_PAGE_CLASS = "iitdev.pagebs";
	private static final String CONSTANT_RESULT = "iitdev.result";
	private static final String CONSTANT_MD5KEY="iitdev.md5_key";
	private static final String CONSTANT_SESSION_KEY="iitdev.session_key";
	private static final String CONSTANT_SESSION_RES_KEY="iitdev.session_res_key";
	private static final String CONSTANT_UPLOAD_DIR="iitdev.upload_dir";
	private static final String PROPERTIES_FILE_NAME="/iitdev.properties";
	private PropertiesLoader loader;
	private static PropertiesConstant instance;
	private PropertiesConstant() {
		loadResouce();
	}
	public static PropertiesConstant getInstance() {
	    if(instance==null){
	    	instance=new PropertiesConstant();
	    }
	    return instance;
	}
	public void loadResouce() {
	    this.loader = new PropertiesLoader(new String[] { PropertiesConstant.PROPERTIES_FILE_NAME });
	 }
	
	private static int getIntValue(String key,int defaultValue){
		PropertiesConstant fk = getInstance();
	    try {
	      if (fk.loader != null) {
	        String value = fk.loader.getProperty(key, defaultValue+"");
	        return  Integer.parseInt(value); }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return defaultValue;
	}
	
	public static String getUrlIndexFilter() {
		return PropertiesConstant.getStringValue(CONSTANT_URL_INDEX_FILTER, "\\.*/((login)|(loginout)|(login_ajax)).htm");
	}
	
	private static String getStringValue(String key,String defaultValue){
		PropertiesConstant fk = getInstance();
	    try {
	      if (fk.loader != null) {
	        String value = fk.loader.getProperty(key, defaultValue);
	        return  value; }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return defaultValue;
	}
	
	public static int getPageSize() {
		return PropertiesConstant.getIntValue(CONSTANT_PAGE_SIZE, 20);
	  }
	
	private static String getUploadDir() {
		return PropertiesConstant.getStringValue(CONSTANT_UPLOAD_DIR, "/mnt/tomcat_upload/ioms");
	}
	
	public static String getUploadDirInterview() {
		return getUploadDir()+File.separator+"interview";
	}
	
	public static String getUploadDirProfile() {
		return getUploadDir()+File.separator+"profile";
	}
	
	public static String getUploadDirCost() {
		return getUploadDir()+File.separator+"cost";
	}
	
	public static String getResult() {
		return PropertiesConstant.getStringValue(CONSTANT_RESULT, "result");
	  }
	
	public static String getBiuProject() {
		return PropertiesConstant.getStringValue(CONSTANT_BIU_PROJECT, "biu");
	  }
	public static String getBiuLocation() {
		return PropertiesConstant.getStringValue(CONSTANT_BIU_LOCATION, "localhost:8080");
	  }
	public static String getPageImplClass() {
		return PropertiesConstant.getStringValue(CONSTANT_PAGE_CLASS, "com.iitdev.system.tag.PageBSImpl");
	  }
	
	public static String getMD5Key(){
		return PropertiesConstant.getStringValue(CONSTANT_MD5KEY, "ioms");
	}
	
	public static String getSessionKey(){
		return PropertiesConstant.getStringValue(CONSTANT_SESSION_KEY, "loginInfo");
	}
	public static String getSessionResKey(){
		return PropertiesConstant.getStringValue(CONSTANT_SESSION_RES_KEY, "staff_res");
	}
}
