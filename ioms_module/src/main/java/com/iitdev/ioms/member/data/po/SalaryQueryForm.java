package com.iitdev.ioms.member.data.po;
import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * SalaryListSearch
 *
 */
public class SalaryQueryForm extends QueryFormBean{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	private String salaryStaffName;//用户真实名
	private String salaryDateMonths;//月份
	private String salaryAttendance;//应出勤
	private String salaryFactAttendance;//实际出勤
	private String salaryCount;//salaryCount
	private String salaryFactCount;//salaryFactCount
	
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
	
	public String getSalaryStaffName() {
		return salaryStaffName;
	}
	public void setSalaryStaffName(String salaryStaffName) {
		this.salaryStaffName = salaryStaffName;
	}
	public String getSalaryDateMonths() {
		return salaryDateMonths;
	}
	public void setSalaryDateMonths(String salaryDateMonths) {
		this.salaryDateMonths = salaryDateMonths;
	}
	public String getSalaryAttendance() {
		return salaryAttendance;
	}
	public void setSalaryAttendance(String salaryAttendance) {
		this.salaryAttendance = salaryAttendance;
	}
	public String getSalaryFactAttendance() {
		return salaryFactAttendance;
	}
	public void setSalaryFactAttendance(String salaryFactAttendance) {
		this.salaryFactAttendance = salaryFactAttendance;
	}
	public String getSalaryCount() {
		return salaryCount;
	}
	public void setSalaryCount(String salaryCount) {
		this.salaryCount = salaryCount;
	}
	public String getSalaryFactCount() {
		return salaryFactCount;
	}
	public void setSalaryFactCount(String salaryFactCount) {
		this.salaryFactCount = salaryFactCount;
	}
	
}