package com.iitdev.ioms.member.data.bo;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * Salary
 */
@Table(name = "m_salary")
public class Salary implements Serializable{
	private static final long serialVersionUID = 1L;
	public Salary(){}
	//属性
	private Long salaryId;//salaryId
	private Long salaryStaff;//用户
	private String salaryDateMonths;//月份
	private BigDecimal salaryAttendance;//应出勤
	private BigDecimal salaryOvertime;//
	private BigDecimal salaryFactAttendance;//实际出勤
	private BigDecimal salaryBase;//基本工资
	private BigDecimal salaryPosition;//岗位工资
	private BigDecimal salaryPerformance;//绩效工资
	private BigDecimal salaryAdd;//其他工资(补贴)
	private BigDecimal salarySupply;//补发工资
	private BigDecimal salaryAbsence;//缺勤
	private BigDecimal salaryPerformanceSub;//绩效扣项
	private BigDecimal salaryLate;//迟到早退
	private BigDecimal salaryInsurance;//保险
	private BigDecimal salaryOther;//其他
	private BigDecimal salaryTax;//个税
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "salary_id", unique = true, nullable = false,length = 19)
	public Long getSalaryId() {
		return this.salaryId;
	}
	
	public void setSalaryId(Long SalaryId) {
		this.salaryId = SalaryId;
	}
	
	@Column(name = "salary_staff",unique = false,nullable = false,length = 10)
	public Long getSalaryStaff() {
		return this.salaryStaff;
	}
	
	public void setSalaryStaff(Long value) {
		this.salaryStaff = value;
	}
	
	@Column(name = "salary_date_months",unique = false,nullable = false,length = 10)
	public String getSalaryDateMonths() {
		return this.salaryDateMonths;
	}
	
	public void setSalaryDateMonths(String value) {
		this.salaryDateMonths = value;
	}
	
	@Column(name = "salary_attendance",precision=4,scale = 2,unique = false,nullable = false)
	public BigDecimal getSalaryAttendance() {
		return this.salaryAttendance;
	}
	
	public void setSalaryAttendance(BigDecimal value) {
		this.salaryAttendance = value;
	}
	@Column(name = "salary_overtime",precision=4,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryOvertime() {
		return salaryOvertime;
	}

	public void setSalaryOvertime(BigDecimal salaryOvertime) {
		this.salaryOvertime = salaryOvertime;
	}

	@Column(name = "salary_fact_attendance",precision=4,scale = 2,unique = false,nullable = false)
	public BigDecimal getSalaryFactAttendance() {
		return this.salaryFactAttendance;
	}
	
	public void setSalaryFactAttendance(BigDecimal value) {
		this.salaryFactAttendance = value;
	}
	
	@Column(name = "salary_base",precision=8,scale = 2,unique = false,nullable = false)
	public BigDecimal getSalaryBase() {
		return this.salaryBase;
	}
	
	public void setSalaryBase(BigDecimal value) {
		this.salaryBase = value;
	}
	
	@Column(name = "salary_position",precision=8,scale = 2,unique = false,nullable = false)
	public BigDecimal getSalaryPosition() {
		return this.salaryPosition;
	}
	
	public void setSalaryPosition(BigDecimal value) {
		this.salaryPosition = value;
	}
	
	@Column(name = "salary_performance",precision=8,scale = 2,unique = false,nullable = false)
	public BigDecimal getSalaryPerformance() {
		return this.salaryPerformance;
	}
	
	public void setSalaryPerformance(BigDecimal value) {
		this.salaryPerformance = value;
	}
	
	@Column(name = "salary_add",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryAdd() {
		return this.salaryAdd;
	}
	
	public void setSalaryAdd(BigDecimal value) {
		this.salaryAdd = value;
	}
	
	@Column(name = "salary_supply",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalarySupply() {
		return this.salarySupply;
	}
	
	public void setSalarySupply(BigDecimal value) {
		this.salarySupply = value;
	}
	
	@Column(name = "salary_absence",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryAbsence() {
		return this.salaryAbsence;
	}
	
	public void setSalaryAbsence(BigDecimal value) {
		this.salaryAbsence = value;
	}
	
	@Column(name = "salary_performance_sub",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryPerformanceSub() {
		return this.salaryPerformanceSub;
	}
	
	public void setSalaryPerformanceSub(BigDecimal value) {
		this.salaryPerformanceSub = value;
	}
	
	@Column(name = "salary_late",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryLate() {
		return this.salaryLate;
	}
	
	public void setSalaryLate(BigDecimal value) {
		this.salaryLate = value;
	}
	
	@Column(name = "salary_insurance",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryInsurance() {
		return this.salaryInsurance;
	}
	
	public void setSalaryInsurance(BigDecimal value) {
		this.salaryInsurance = value;
	}
	
	@Column(name = "salary_other",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryOther() {
		return this.salaryOther;
	}
	
	public void setSalaryOther(BigDecimal value) {
		this.salaryOther = value;
	}
	
	@Column(name = "salary_tax",precision=8,scale = 2,unique = false,nullable = true)
	public BigDecimal getSalaryTax() {
		return this.salaryTax;
	}
	
	public void setSalaryTax(BigDecimal value) {
		this.salaryTax = value;
	}
	
	
	
}
