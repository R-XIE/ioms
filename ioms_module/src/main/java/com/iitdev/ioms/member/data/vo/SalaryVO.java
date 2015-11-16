package com.iitdev.ioms.member.data.vo;
import com.iitdev.ioms.member.data.bo.Salary;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Salary
 *
 */
public class SalaryVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,staff.staff_real_name as salary_staff_name from m_salary obj"
			+ " inner join m_staff staff on staff.staff_id=obj.salary_staff";
	public static final String QUERY_SQL_COUNT = "select * from m_salary salary where salary.salary_staff=?"
			+ " ORDER BY salary.salary_date_months desc limit 1" ;
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
	
	private String salaryStaffName;
	private BigDecimal salaryCount;
	private BigDecimal salaryCountFact;
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getSalaryId() {
		return this.salaryId;
	}
	public void setSalaryId(Long value) {
		this.salaryId = value;
	}
	public Long getSalaryStaff() {
		return this.salaryStaff;
	}
	public void setSalaryStaff(Long value) {
		this.salaryStaff = value;
	}
	public String getSalaryDateMonths() {
		return this.salaryDateMonths;
	}
	public void setSalaryDateMonths(String value) {
		this.salaryDateMonths = value;
	}
	public BigDecimal getSalaryAttendance() {
		if(salaryAttendance==null)
			this.salaryAttendance=new BigDecimal(0);
		return this.salaryAttendance;
	}
	public void setSalaryAttendance(BigDecimal value) {
		this.salaryAttendance = value;
	}
	public BigDecimal getSalaryFactAttendance() {
		if(salaryFactAttendance==null)
			this.salaryFactAttendance=new BigDecimal(0);
		return this.salaryFactAttendance;
	}
	public void setSalaryFactAttendance(BigDecimal value) {
		this.salaryFactAttendance = value;
	}
	public BigDecimal getSalaryBase() {
		if(salaryBase==null)
			this.salaryBase=new BigDecimal(0);
		return this.salaryBase;
	}
	public void setSalaryBase(BigDecimal value) {
		this.salaryBase = value;
	}
	public BigDecimal getSalaryPosition() {
		if(salaryPosition==null)
			this.salaryPosition=new BigDecimal(0);
		return this.salaryPosition;
	}
	public void setSalaryPosition(BigDecimal value) {
		this.salaryPosition = value;
	}
	public BigDecimal getSalaryPerformance() {
		if(salaryPerformance==null)
			this.salaryPerformance=new BigDecimal(0);
		return this.salaryPerformance;
	}
	public void setSalaryPerformance(BigDecimal value) {
		this.salaryPerformance = value;
	}
	public BigDecimal getSalaryAdd() {
		if(salaryAdd==null)
			this.salaryAdd=new BigDecimal(0);
		return this.salaryAdd;
	}
	public void setSalaryAdd(BigDecimal value) {
		this.salaryAdd = value;
	}
	public BigDecimal getSalarySupply() {
		if(salarySupply==null)
			this.salarySupply=new BigDecimal(0);
		return this.salarySupply;
	}
	public void setSalarySupply(BigDecimal value) {
		this.salarySupply = value;
	}
	public BigDecimal getSalaryAbsence() {
		if(salaryAbsence==null)
			this.salaryAbsence=new BigDecimal(0);
		return this.salaryAbsence;
	}
	public void setSalaryAbsence(BigDecimal value) {
		this.salaryAbsence = value;
	}
	public BigDecimal getSalaryPerformanceSub() {
		if(salaryPerformanceSub==null)
			this.salaryPerformanceSub=new BigDecimal(0);
		return this.salaryPerformanceSub;
	}
	public void setSalaryPerformanceSub(BigDecimal value) {
		this.salaryPerformanceSub = value;
	}
	public BigDecimal getSalaryLate() {
		if(salaryLate==null)
			this.salaryLate=new BigDecimal(0);
		return this.salaryLate;
	}
	public void setSalaryLate(BigDecimal value) {
		this.salaryLate = value;
	}
	public BigDecimal getSalaryInsurance() {
		if(salaryInsurance==null)
			this.salaryInsurance=new BigDecimal(0);
		return this.salaryInsurance;
	}
	public void setSalaryInsurance(BigDecimal value) {
		this.salaryInsurance = value;
	}
	public BigDecimal getSalaryOther() {
		if(salaryOther==null)
			this.salaryOther=new BigDecimal(0);
		return this.salaryOther;
	}
	public void setSalaryOther(BigDecimal value) {
		this.salaryOther = value;
	}
	public BigDecimal getSalaryTax() {
		if(salaryTax==null)
			this.salaryTax=new BigDecimal(0);
		return this.salaryTax;
	}
	public void setSalaryTax(BigDecimal value) {
		this.salaryTax = value;
	}
	
	public String getSalaryStaffName() {
		return salaryStaffName;
	}
	public void setSalaryStaffName(String salaryStaffName) {
		this.salaryStaffName = salaryStaffName;
	}
	public BigDecimal getSalaryCount() {
		this.salaryCount=salaryBase.add(getSalaryPosition()).add(getSalaryPerformance())
				.add(getSalaryAdd()).add(getSalarySupply());
		return salaryCount;
	}
	public void setSalaryCount(BigDecimal salaryCount) {
		this.salaryCount = salaryCount;
	}
	public BigDecimal getSalaryCountFact() {
		this.salaryCountFact=getSalaryCount().subtract(getSalaryAbsence()).subtract(getSalaryPerformanceSub())
				.subtract(getSalaryLate()).subtract(getSalaryInsurance()).subtract(getSalaryOther())
				.subtract(getSalaryTax());
		return salaryCountFact;
	}
	public void setSalaryCountFact(BigDecimal salaryCountFact) {
		this.salaryCountFact = salaryCountFact;
	}
	
	//////////////////////////////////////////////////////
	public SalaryVO(){}

	public SalaryVO(Salary salary){
		BeanUtils.copyProperties(salary,this);
	}
	public void copyValueTo(Salary bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Salary cloneBO(){
		Salary bo = new Salary();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Salary> cloneBOList(List<SalaryVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Salary>(0);
		List<Salary> result = new ArrayList<Salary>(vos.size());
		for(SalaryVO vo: vos){
			Salary bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
	public BigDecimal getSalaryOvertime() {
		if(salaryOvertime==null)
			this.salaryOvertime=new BigDecimal(0);
		return this.salaryOvertime;
	}
	public void setSalaryOvertime(BigDecimal salaryOvertime) {
		this.salaryOvertime = salaryOvertime;
	}
}