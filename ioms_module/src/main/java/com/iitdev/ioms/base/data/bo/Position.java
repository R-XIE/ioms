package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Position
 */
@Table(name = "b_position")
public class Position implements Serializable{
	private static final long serialVersionUID = 1L;
	public Position(){}
	//属性
	private Long positionId;//主键ID
	private Long branchId;//部门
	private String positionName;//岗位名称
	private String positionDesc;//岗位描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "position_id", unique = true, nullable = false,length = 10)
	public Long getPositionId() {
		return this.positionId;
	}
	
	public void setPositionId(Long PositionId) {
		this.positionId = PositionId;
	}
	
	
	@Column(name = "branch_id", unique = false, nullable = false,length = 10)
	public Long getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(Long BranchId) {
		this.branchId = BranchId;
	}
	
	@Column(name = "position_name",unique = false,nullable = false,length = 50)
	public String getPositionName() {
		return this.positionName;
	}
	
	public void setPositionName(String value) {
		this.positionName = value;
	}
	
	@Column(name = "position_desc",unique = false,nullable = true,length = 100)
	public String getPositionDesc() {
		return this.positionDesc;
	}
	
	public void setPositionDesc(String value) {
		this.positionDesc = value;
	}
	
	
	
}
