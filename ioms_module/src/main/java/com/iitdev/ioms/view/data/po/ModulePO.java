package com.iitdev.ioms.view.data.po;
import java.io.Serializable;
import java.util.List;

import com.iitdev.globals.ResourcesPO;

/**
 * ModuleStaff
 *
 */
public class ModulePO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long moduleId;//资源ID
	private String moduleName;//资源名称
	private String moduleCss;
	private List<ResourcesPO> resources;
	/////////////////////////getter and setter //////////////////////////
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleCss() {
		return moduleCss;
	}
	public void setModuleCss(String moduleCss) {
		this.moduleCss = moduleCss;
	}
	public List<ResourcesPO> getResources() {
		return resources;
	}
	public void setResources(List<ResourcesPO> resources) {
		this.resources = resources;
	}
	
}