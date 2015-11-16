package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Project
 */
@Table(name = "b_project")
public class Project implements Serializable{
	private static final long serialVersionUID = 1L;
	public Project(){}
	//属性
	private Long projectId;//项目ID
	private String projectCode;//项目编号
	private String projectName;//项目名称
	private Long staffId;//项目负责人
	private String projectDescription;//项目描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false,length = 10)
	public Long getProjectId() {
		return this.projectId;
	}
	
	public void setProjectId(Long ProjectId) {
		this.projectId = ProjectId;
	}
	
	@Column(name = "project_code",unique = false,nullable = true,length = 255)
	public String getProjectCode() {
		return this.projectCode;
	}
	
	public void setProjectCode(String value) {
		this.projectCode = value;
	}
	
	@Column(name = "project_name",unique = false,nullable = true,length = 255)
	public String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(String value) {
		this.projectName = value;
	}
	
	@Column(name = "staff_id",unique = false,nullable = true,length = 10)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	@Column(name = "project_description",unique = false,nullable = true,length = 255)
	public String getProjectDescription() {
		return this.projectDescription;
	}
	
	public void setProjectDescription(String value) {
		this.projectDescription = value;
	}
	
	
	
}
