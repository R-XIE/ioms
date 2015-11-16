package com.iitdev.ioms.member.data.vo;
import com.iitdev.ioms.member.data.bo.Interview;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Interview
 *
 */
public class InterviewVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from m_interview obj" ;
	private Long interviewId;//主键ID
	private String interviewName;//面试姓名
	private String interviewToName;//面试官姓名
	private Long interviewSex;//面试性别
	private Long interviewPosition;//面试职位
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
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
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getInterviewId() {
		return this.interviewId;
	}
	public void setInterviewId(Long value) {
		this.interviewId = value;
	}
	public String getInterviewName() {
		return this.interviewName;
	}
	public void setInterviewName(String value) {
		this.interviewName = value;
	}
	public String getInterviewToName() {
		return this.interviewToName;
	}
	
	public void setInterviewToName(String value) {
		this.interviewToName = value;
	}
	public Long getInterviewSex() {
		return this.interviewSex;
	}
	public void setInterviewSex(Long value) {
		this.interviewSex = value;
	}
	public Long getInterviewPosition() {
		return this.interviewPosition;
	}
	public void setInterviewPosition(Long value) {
		this.interviewPosition = value;
	}
	public Date getInterviewDate() {
		return this.interviewDate;
	}
	public void setInterviewDate(Date value) {
		this.interviewDate = value;
	}
	public String getInterviewPhone() {
		return this.interviewPhone;
	}
	public void setInterviewPhone(String value) {
		this.interviewPhone = value;
	}
	public Long getInterviewCurrent() {
		return this.interviewCurrent;
	}
	public void setInterviewCurrent(Long value) {
		this.interviewCurrent = value;
	}
	public String getInterviewCurrentWork() {
		return this.interviewCurrentWork;
	}
	public void setInterviewCurrentWork(String value) {
		this.interviewCurrentWork = value;
	}
	public String getInterviewQuit() {
		return this.interviewQuit;
	}
	public void setInterviewQuit(String value) {
		this.interviewQuit = value;
	}
	public BigDecimal getInterviewSalary() {
		return this.interviewSalary;
	}
	public void setInterviewSalary(BigDecimal value) {
		this.interviewSalary = value;
	}
	public BigDecimal getInterviewScore() {
		return this.interviewScore;
	}
	public void setInterviewScore(BigDecimal value) {
		this.interviewScore = value;
	}
	public Long getInterviewResult() {
		return this.interviewResult;
	}
	public void setInterviewResult(Long value) {
		this.interviewResult = value;
	}
	public String getInterviewCv() {
		return this.interviewCv;
	}
	public void setInterviewCv(String value) {
		this.interviewCv = value;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	
	//////////////////////////////////////////////////////
	public InterviewVO(){}

	public InterviewVO(Interview interview){
		BeanUtils.copyProperties(interview,this);
	}
	public void copyValueTo(Interview bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Interview cloneBO(){
		Interview bo = new Interview();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Interview> cloneBOList(List<InterviewVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Interview>(0);
		List<Interview> result = new ArrayList<Interview>(vos.size());
		for(InterviewVO vo: vos){
			Interview bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}