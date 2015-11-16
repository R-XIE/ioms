package com.iitdev.ioms.member.data.po;

import java.math.BigDecimal;
import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * InterviewListSearch
 *
 */
public class InterviewQueryForm extends QueryFormBean{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	
	private String interviewName;//面试姓名（应聘人）
	private Long interviewSex;//interviewSexName
	private Long interviewPosition;//职位
	private String interviewDateBE;//interviewDateLable
	private Long interviewCurrent;//interviewCurrentName
	private String interviewPhone;//面试联系方式
	private BigDecimal interviewSalary;//期望薪水
	private String interviewScore;//面试评分
	private Long interviewResult;//interviewResultName
	/////////////////////////getter and setter //////////////////////////
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
	public String getInterviewName() {
		return interviewName;
	}
	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}
	public Long getInterviewSex() {
		return interviewSex;
	}
	public void setInterviewSex(Long interviewSex) {
		this.interviewSex = interviewSex;
	}
	public Long getInterviewPosition() {
		return interviewPosition;
	}
	public void setInterviewPosition(Long interviewPosition) {
		this.interviewPosition = interviewPosition;
	}
	public String getInterviewDateBE() {
		return interviewDateBE;
	}
	public void setInterviewDateBE(String interviewDateBE) {
		this.interviewDateBE = interviewDateBE;
	}
	public Long getInterviewCurrent() {
		return interviewCurrent;
	}
	public void setInterviewCurrent(Long interviewCurrent) {
		this.interviewCurrent = interviewCurrent;
	}
	public String getInterviewPhone() {
		return interviewPhone;
	}
	public void setInterviewPhone(String interviewPhone) {
		this.interviewPhone = interviewPhone;
	}
	public BigDecimal getInterviewSalary() {
		return interviewSalary;
	}
	public void setInterviewSalary(BigDecimal interviewSalary) {
		this.interviewSalary = interviewSalary;
	}
	public String getInterviewScore() {
		return interviewScore;
	}
	public void setInterviewScore(String interviewScore) {
		this.interviewScore = interviewScore;
	}
	public Long getInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(Long interviewResult) {
		this.interviewResult = interviewResult;
	}
	
}