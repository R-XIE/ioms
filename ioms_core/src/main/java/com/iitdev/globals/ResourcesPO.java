package com.iitdev.globals;
import java.io.Serializable;

/**
 * ResourcesStaff
 *
 */
public class ResourcesPO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long resourcesId;//资源ID
	private String resourcesName;//资源名称
	private String resourcesUrl;//资源路径(URL)
	private String resourcesCss;
	private Long resourcesSuper;
	////
	//private Boolean isAuth;//资源是否授权
	public String getResourcesCss() {
		return resourcesCss;
	}
	public void setResourcesCss(String resourcesCss) {
		this.resourcesCss = resourcesCss;
	}
	public Long getResourcesSuper() {
		return resourcesSuper;
	}
	public void setResourcesSuper(Long resourcesSuper) {
		this.resourcesSuper = resourcesSuper;
	}
	/////////////////////////getter and setter //////////////////////////
	public Long getResourcesId() {
		return this.resourcesId;
	}
	public void setResourcesId(Long value) {
		this.resourcesId = value;
	}
	
	public String getResourcesName() {
		return this.resourcesName;
	}
	public void setResourcesName(String value) {
		this.resourcesName = value;
	}
	public String getResourcesUrl() {
		return this.resourcesUrl;
	}
	public void setResourcesUrl(String value) {
		this.resourcesUrl = value;
	}
	
	//////////////////////////////////////////////////////
	public ResourcesPO(){}
//	public Boolean getIsAuth() {
//		return isAuth;
//	}
//	public void setIsAuth(Boolean isAuth) {
//		this.isAuth = isAuth;
//	}
}