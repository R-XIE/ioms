package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.Branch;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Branch
 *
 */
public class BranchVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from b_branch obj" ;
	private Long branchId;//主键ID
	private String branchName;//部门名称
	private String branchDesc;//部门描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getBranchId() {
		return this.branchId;
	}
	public void setBranchId(Long value) {
		this.branchId = value;
	}
	public String getBranchName() {
		return this.branchName;
	}
	public void setBranchName(String value) {
		this.branchName = value;
	}
	public String getBranchDesc() {
		return this.branchDesc;
	}
	public void setBranchDesc(String value) {
		this.branchDesc = value;
	}
	
	
	//////////////////////////////////////////////////////
	public BranchVO(){}

	public BranchVO(Branch branch){
		BeanUtils.copyProperties(branch,this);
	}
	public void copyValueTo(Branch bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Branch cloneBO(){
		Branch bo = new Branch();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Branch> cloneBOList(List<BranchVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Branch>(0);
		List<Branch> result = new ArrayList<Branch>(vos.size());
		for(BranchVO vo: vos){
			Branch bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}