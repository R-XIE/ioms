package com.iitdev.ioms.member.data.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.SalaryAlter;

/**
 * SalaryAlter
 *
 */
public class SalaryAlterVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = " select obj.*,position.position_name,branch.branch_name,staff.staff_entry_date from m_salary_alter obj  "
			+ " INNER JOIN m_staff staff on staff.staff_id=obj.staff_id  "
			+ " INNER JOIN b_position position on position.position_id=staff.position_id "
			+ " INNER JOIN b_branch branch on branch.branch_id=position.branch_id" ;
	private Long alterId;//主键ID
	private Long staffId;//申请人的名字、部门、职位、入职日期
	private String alterCause;//异动原因[调薪、调岗、调职、转正]
	private BigDecimal alterBefore;//原薪资标准
	private BigDecimal alterAfter;//异动后薪资标准
	@DateTimeFormat(pattern="yyyy-MM")
	private String alterDate;//生效月份
	private Date alterCreateDate;//生效月份
	//页面显示数据
	private String positionName;
	private String branchName;
	private Date staffEntryDate;
	private String alterCauseLable;
	/////////////////////////getter and setter //////////////////////////
	public Long getAlterId() {
		return this.alterId;
	}
	public void setAlterId(Long value) {
		this.alterId = value;
	}
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	public String getAlterCause() {
		return this.alterCause;
	}
	public void setAlterCause(String value) {
		this.alterCause = value;
//		String[]indexArr=this.alterCause.split(",");
//		StringBuffer sb =new StringBuffer();
//		for (String str : indexArr) {
//			sb.append(Globals.M_ALTER_CAUSE.get(new Long(str)));
//			sb.append(",");
//		}
//		sb.deleteCharAt(sb.lastIndexOf(","));
//		this.alterCauseLable=sb.toString();
	}
	public BigDecimal getAlterBefore() {
		return this.alterBefore;
	}
	public void setAlterBefore(BigDecimal value) {
		this.alterBefore = value;
	}
	public BigDecimal getAlterAfter() {
		return this.alterAfter;
	}
	public void setAlterAfter(BigDecimal value) {
		this.alterAfter = value;
	}
	public String getAlterDate() {
		return this.alterDate;
	}
	public void setAlterDate(String value) {
		this.alterDate = value;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getStaffEntryDate() {
		return staffEntryDate;
	}
	public void setStaffEntryDate(Date staffEntryDate) {
		this.staffEntryDate = staffEntryDate;
	}
	public Date getAlterCreateDate() {
		return alterCreateDate;
	}
	public void setAlterCreateDate(Date alterCreateDate) {
		this.alterCreateDate = alterCreateDate;
	}
	public String getAlterCauseLable() {
		String[]indexArr=this.alterCause.split(",");
		StringBuffer sb =new StringBuffer();
		for (String str : indexArr) {
			sb.append(Globals.M_ALTER_CAUSE.get(new Long(str)));
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		this.alterCauseLable=sb.toString();
		return alterCauseLable;
	}
	public void setAlterCauseLable(String alterCauseLable) {
		this.alterCauseLable = alterCauseLable;
	}
	//////////////////////////////////////////////////////
	public SalaryAlterVO(){}

	public SalaryAlterVO(SalaryAlter salaryAlter){
		BeanUtils.copyProperties(salaryAlter,this);
	}
	public void copyValueTo(SalaryAlter bo){
		BeanUtils.copyProperties(this,bo);
	}
	public SalaryAlter cloneBO(){
		SalaryAlter bo = new SalaryAlter();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<SalaryAlter> cloneBOList(List<SalaryAlterVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<SalaryAlter>(0);
		List<SalaryAlter> result = new ArrayList<SalaryAlter>(vos.size());
		for(SalaryAlterVO vo: vos){
			SalaryAlter bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}