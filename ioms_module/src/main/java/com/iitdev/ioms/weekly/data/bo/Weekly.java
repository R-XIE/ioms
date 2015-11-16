package com.iitdev.ioms.weekly.data.bo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Weekly
 */
@Table(name = "w_weekly")
public class Weekly implements Serializable{
	private static final long serialVersionUID = 1L;
	public Weekly(){}
	//属性
	private Long weeklyId;//weeklyId
	private Date weeklyBeginDate;//周报开始日期
	private Date weeklyEndDate;//周报结束日期
	private Long weeklyStaff;//用户
	private String weeklyLastSummary;//上周工作总结
	private String weeklyThisSummary;//本周工作内容
	private String weeklyNextSummary;//下周工作
	private String weeklyProblem;//工作遇到的问题
	private String workNotes;//工作注意事项
	private Date weeklyCreateDate;//编写日期
	private Long weeklyState;//weeklyState
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weekly_id", unique = true, nullable = false,length = 19)
	public Long getWeeklyId() {
		return this.weeklyId;
	}
	
	public void setWeeklyId(Long WeeklyId) {
		this.weeklyId = WeeklyId;
	}
	
	@Column(name = "weekly_begin_date",unique = false,nullable = false,length = 0)
	public Date getWeeklyBeginDate() {
		return this.weeklyBeginDate;
	}
	
	public void setWeeklyBeginDate(Date value) {
		this.weeklyBeginDate = value;
	}
	
	@Column(name = "weekly_end_date",unique = false,nullable = false,length = 0)
	public Date getWeeklyEndDate() {
		return this.weeklyEndDate;
	}
	
	public void setWeeklyEndDate(Date value) {
		this.weeklyEndDate = value;
	}
	
	@Column(name = "weekly_staff",unique = false,nullable = false,length =20)
	public Long getWeeklyStaff() {
		return this.weeklyStaff;
	}
	
	public void setWeeklyStaff(Long value) {
		this.weeklyStaff = value;
	}
	
	@Column(name = "weekly_last_summary",unique = false,nullable = false,length = 65535)
	public String getWeeklyLastSummary() {
		return this.weeklyLastSummary;
	}
	
	public void setWeeklyLastSummary(String value) {
		this.weeklyLastSummary = value;
	}
	
	@Column(name = "weekly_this_summary",unique = false,nullable = false,length = 65535)
	public String getWeeklyThisSummary() {
		return this.weeklyThisSummary;
	}
	
	public void setWeeklyThisSummary(String value) {
		this.weeklyThisSummary = value;
	}
	
	@Column(name = "weekly_next_summary",unique = false,nullable = false,length = 65535)
	public String getWeeklyNextSummary() {
		return this.weeklyNextSummary;
	}
	
	public void setWeeklyNextSummary(String value) {
		this.weeklyNextSummary = value;
	}
	
	@Column(name = "weekly_problem",unique = false,nullable = true,length = 65535)
	public String getWeeklyProblem() {
		return this.weeklyProblem;
	}
	
	public void setWeeklyProblem(String value) {
		this.weeklyProblem = value;
	}
	
	@Column(name = "work_notes",unique = false,nullable = true,length = 65535)
	public String getWorkNotes() {
		return this.workNotes;
	}
	
	public void setWorkNotes(String value) {
		this.workNotes = value;
	}
	
	@Column(name = "weekly_create_date",unique = false,nullable = false,length = 0)
	public Date getWeeklyCreateDate() {
		return this.weeklyCreateDate;
	}
	
	public void setWeeklyCreateDate(Date value) {
		this.weeklyCreateDate = value;
	}
	
	@Column(name = "weekly_state",unique = false,nullable = false,length = 5)
	public Long getWeeklyState() {
		return this.weeklyState;
	}
	
	public void setWeeklyState(Long value) {
		this.weeklyState = value;
	}
	
	
	
}
