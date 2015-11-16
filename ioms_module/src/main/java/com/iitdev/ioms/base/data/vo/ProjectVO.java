package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.Project;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Project
 *
 */
public class ProjectVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from b_project obj" ;
	private Long projectId;//项目ID
	private String projectCode;//项目编号
	private String projectName;//项目名称
	private Long staffId;//项目负责人
	private String projectDescription;//项目描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Long value) {
		this.projectId = value;
	}
	public String getProjectCode() {
		return this.projectCode;
	}
	public void setProjectCode(String value) {
		this.projectCode = value;
	}
	public String getProjectName() {
		return this.projectName;
	}
	public void setProjectName(String value) {
		this.projectName = value;
	}
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	public String getProjectDescription() {
		return this.projectDescription;
	}
	public void setProjectDescription(String value) {
		this.projectDescription = value;
	}
	
	
	//////////////////////////////////////////////////////
	public ProjectVO(){}

	public ProjectVO(Project project){
		BeanUtils.copyProperties(project,this);
	}
	public void copyValueTo(Project bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Project cloneBO(){
		Project bo = new Project();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Project> cloneBOList(List<ProjectVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Project>(0);
		List<Project> result = new ArrayList<Project>(vos.size());
		for(ProjectVO vo: vos){
			Project bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}