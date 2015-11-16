package com.iitdev.ioms.weekly.data.vo;
import com.iitdev.ioms.weekly.data.bo.Weekly;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * Weekly
 *
 */
public class WeeklyVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,staff.staff_real_name as weekly_staff_name from w_weekly obj "
			+ "inner join m_staff staff on staff.staff_id=obj.weekly_staff" ;
	public static final String QUERY_SQL_COUNT = "select count(1) from w_weekly obj " ;
	private Long weeklyId;//weeklyId
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date weeklyBeginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date weeklyEndDate;
	private Long weeklyStaff;//用户
	private String weeklyLastSummary;//上周工作总结
	private String weeklyThisSummary;//本周工作内容
	private String weeklyNextSummary;//下周工作
	private String weeklyProblem;//工作遇到的问题
	private String workNotes;//工作注意事项
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date weeklyCreateDate;
	private Long weeklyState;//weeklyState
	
	//
	private String weeklyStaffName;
	/////////////////////////getter and setter //////////////////////////
	public Long getWeeklyId() {
		return this.weeklyId;
	}
	public void setWeeklyId(Long value) {
		this.weeklyId = value;
	}
	public Date getWeeklyBeginDate() {
		return this.weeklyBeginDate;
	}
	public void setWeeklyBeginDate(Date value) {
		this.weeklyBeginDate = value;
	}
	public Date getWeeklyEndDate() {
		return this.weeklyEndDate;
	}
	public void setWeeklyEndDate(Date value) {
		this.weeklyEndDate = value;
	}
	public Long getWeeklyStaff() {
		return this.weeklyStaff;
	}
	public void setWeeklyStaff(Long value) {
		this.weeklyStaff = value;
	}
	public String getWeeklyLastSummary() {
		return this.weeklyLastSummary;
	}
	public void setWeeklyLastSummary(String value) {
		this.weeklyLastSummary = value;
	}
	public String getWeeklyThisSummary() {
		return this.weeklyThisSummary;
	}
	public void setWeeklyThisSummary(String value) {
		this.weeklyThisSummary = value;
	}
	public String getWeeklyNextSummary() {
		return this.weeklyNextSummary;
	}
	public void setWeeklyNextSummary(String value) {
		this.weeklyNextSummary = value;
	}
	public String getWeeklyProblem() {
		return this.weeklyProblem;
	}
	public void setWeeklyProblem(String value) {
		this.weeklyProblem = value;
	}
	public String getWorkNotes() {
		return this.workNotes;
	}
	public void setWorkNotes(String value) {
		this.workNotes = value;
	}
	public Date getWeeklyCreateDate() {
		return this.weeklyCreateDate;
	}
	public void setWeeklyCreateDate(Date value) {
		this.weeklyCreateDate = value;
	}
	public Long getWeeklyState() {
		return this.weeklyState;
	}
	public void setWeeklyState(Long value) {
		this.weeklyState = value;
	}
	
	
	public String getWeeklyStaffName() {
		return weeklyStaffName;
	}
	public void setWeeklyStaffName(String weeklyStaffName) {
		this.weeklyStaffName = weeklyStaffName;
	}
	//////////////////////////////////////////////////////
	public WeeklyVO(){}

	public WeeklyVO(Weekly weekly){
		BeanUtils.copyProperties(weekly,this);
	}
	public void copyValueTo(Weekly bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Weekly cloneBO(){
		Weekly bo = new Weekly();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Weekly> cloneBOList(List<WeeklyVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Weekly>(0);
		List<Weekly> result = new ArrayList<Weekly>(vos.size());
		for(WeeklyVO vo: vos){
			Weekly bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}