package com.iitdev.ioms.member.data.bo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SalaryAlter
 */
@Table(name = "m_salary_alter")
public class SalaryAlter implements Serializable{
	private static final long serialVersionUID = 1L;
	public SalaryAlter(){}
	//属性
	private Long alterId;//主键ID
	private Long staffId;//申请人的名字、部门、职位、入职日期
	private String alterCause;//异动原因[调薪、调岗、调职、转正]
	private BigDecimal alterBefore;//原薪资标准
	private BigDecimal alterAfter;//异动后薪资标准
	private String alterDate;//生效月份
	private Date alterCreateDate;//生效月份
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alter_id", unique = true, nullable = false,length = 10)
	public Long getAlterId() {
		return this.alterId;
	}
	
	public void setAlterId(Long AlterId) {
		this.alterId = AlterId;
	}
	
	@Column(name = "staff_id",unique = false,nullable = false,length = 10)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	@Column(name = "alter_cause",unique = false,nullable = false,length = 10)
	public String getAlterCause() {
		return this.alterCause;
	}
	
	public void setAlterCause(String value) {
		this.alterCause = value;
	}
	
	@Column(name = "alter_before",unique = false,nullable = false,length = 7)
	public BigDecimal getAlterBefore() {
		return this.alterBefore;
	}
	
	public void setAlterBefore(BigDecimal value) {
		this.alterBefore = value;
	}
	
	@Column(name = "alter_after",unique = false,nullable = false,length = 7)
	public BigDecimal getAlterAfter() {
		return this.alterAfter;
	}
	
	public void setAlterAfter(BigDecimal value) {
		this.alterAfter = value;
	}
	
	@Column(name = "alter_date",unique = false,nullable = false,length = 20)
	public String getAlterDate() {
		return this.alterDate;
	}
	
	public void setAlterDate(String value) {
		this.alterDate = value;
	}
	
	@Column(name = "alter_create_date",unique = false,nullable = false,length = 0)
	public Date getAlterCreateDate() {
		return alterCreateDate;
	}

	public void setAlterCreateDate(Date alterCreateDate) {
		this.alterCreateDate = alterCreateDate;
	}
	
}
