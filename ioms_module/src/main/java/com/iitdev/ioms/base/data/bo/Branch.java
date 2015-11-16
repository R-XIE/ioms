package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Branch
 */
@Table(name = "b_branch")
public class Branch implements Serializable{
	private static final long serialVersionUID = 1L;
	public Branch(){}
	//属性
	private Long branchId;//主键ID
	private String branchName;//部门名称
	private String branchDesc;//部门描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id", unique = true, nullable = false,length = 10)
	public Long getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(Long BranchId) {
		this.branchId = BranchId;
	}
	
	@Column(name = "branch_name",unique = false,nullable = false,length = 50)
	public String getBranchName() {
		return this.branchName;
	}
	
	public void setBranchName(String value) {
		this.branchName = value;
	}
	
	@Column(name = "branch_desc",unique = false,nullable = true,length = 100)
	public String getBranchDesc() {
		return this.branchDesc;
	}
	
	public void setBranchDesc(String value) {
		this.branchDesc = value;
	}
	
	
	
}
