package com.iitdev.ioms.member.data.bo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Interview
 */
@Table(name = "m_interview")
public class Interview implements Serializable{
	private static final long serialVersionUID = 1L;
	public Interview(){}
	//属性
	private Long interviewId;//主键ID
	private String interviewName;//应聘者姓名
	private String interviewToName;//面试官姓名
	private Long interviewSex;//面试性别
	private Long interviewPosition;//面试职位
	private Date interviewDate;//面试职位
	private String interviewPhone;//面试联系方式
	private Long interviewCurrent;//当前状况
	private String interviewCurrentWork;//现工作单位
	private String interviewQuit;//跳槽原因
	private BigDecimal interviewSalary;//期望薪水
	private BigDecimal interviewScore;//面试评分
	private Long interviewResult;//面试结果
	private String interviewCv;//面试简历,上传word或者上传zip压缩
	private Date createTime;//记录日期
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id", unique = true, nullable = false,length = 10)
	public Long getInterviewId() {
		return this.interviewId;
	}
	
	public void setInterviewId(Long InterviewId) {
		this.interviewId = InterviewId;
	}
	
	@Column(name = "interview_name",unique = false,nullable = false,length = 50)
	public String getInterviewName() {
		return this.interviewName;
	}
	
	public void setInterviewName(String value) {
		this.interviewName = value;
	}
	
	@Column(name = "interview_to_name",unique = false,nullable = false,length = 50)
	public String getInterviewToName() {
		return this.interviewToName;
	}
	
	public void setInterviewToName(String value) {
		this.interviewToName = value;
	}
	
	@Column(name = "interview_sex",unique = false,nullable = false,length = 10)
	public Long getInterviewSex() {
		return this.interviewSex;
	}
	
	public void setInterviewSex(Long value) {
		this.interviewSex = value;
	}
	
	@Column(name = "interview_position",unique = false,nullable = false,length = 10)
	public Long getInterviewPosition() {
		return this.interviewPosition;
	}
	
	public void setInterviewPosition(Long value) {
		this.interviewPosition = value;
	}
	
	@Column(name = "interview_date",unique = false,nullable = false,length = 0)
	public Date getInterviewDate() {
		return this.interviewDate;
	}
	
	public void setInterviewDate(Date value) {
		this.interviewDate = value;
	}
	
	@Column(name = "interview_phone",unique = false,nullable = false,length = 50)
	public String getInterviewPhone() {
		return this.interviewPhone;
	}
	
	public void setInterviewPhone(String value) {
		this.interviewPhone = value;
	}
	
	@Column(name = "interview_current",unique = false,nullable = false,length = 3)
	public Long getInterviewCurrent() {
		return this.interviewCurrent;
	}
	
	public void setInterviewCurrent(Long value) {
		this.interviewCurrent = value;
	}
	
	@Column(name = "interview_current_work",unique = false,nullable = false,length = 50)
	public String getInterviewCurrentWork() {
		return this.interviewCurrentWork;
	}
	
	public void setInterviewCurrentWork(String value) {
		this.interviewCurrentWork = value;
	}
	
	@Column(name = "interview_quit",unique = false,nullable = false,length = 50)
	public String getInterviewQuit() {
		return this.interviewQuit;
	}
	
	public void setInterviewQuit(String value) {
		this.interviewQuit = value;
	}
	
	@Column(name = "interview_salary",unique = false,nullable = false,length = 50)
	public BigDecimal getInterviewSalary() {
		return this.interviewSalary;
	}
	
	public void setInterviewSalary(BigDecimal value) {
		this.interviewSalary = value;
	}
	
	@Column(name = "interview_score",unique = false,nullable = false,length = 50)
	public BigDecimal getInterviewScore() {
		return this.interviewScore;
	}
	
	public void setInterviewScore(BigDecimal value) {
		this.interviewScore = value;
	}
	
	@Column(name = "interview_result",unique = false,nullable = false,length = 3)
	public Long getInterviewResult() {
		return this.interviewResult;
	}
	
	public void setInterviewResult(Long value) {
		this.interviewResult = value;
	}
	
	@Column(name = "interview_CV",unique = false,nullable = false,length = 100)
	public String getInterviewCv() {
		return this.interviewCv;
	}
	
	public void setInterviewCv(String value) {
		this.interviewCv = value;
	}
	
	@Column(name = "create_time",unique = false,nullable = false,length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	
	
}
