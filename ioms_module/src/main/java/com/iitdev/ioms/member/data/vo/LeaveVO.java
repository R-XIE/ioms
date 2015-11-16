package com.iitdev.ioms.member.data.vo;
import com.iitdev.ioms.member.data.bo.Leave;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Leave
 *
 */
public class LeaveVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,staff.position_id,branch.branch_id ,position.position_name,branch.branch_name from m_leave obj "
			+ "INNER JOIN m_staff staff on staff.staff_id=obj.staff_id "
			+ "INNER JOIN b_position position on position.position_id=staff.position_id "
			+ "INNER JOIN b_branch branch on branch.branch_id=position.branch_id" ;
	
	private Long leaveId;//主键ID
	private Long staffId;//请假人
	private Long leaveType;//请假类别[事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假]
	private String leaveSubject;//请假事由
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date leaveDateBegin;//请假开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date leaveDateEnd;//请假结束时间(自动计算共计x天y时)
	private BigDecimal leaveDays;
	private Date createDate;//填写时间
	
	private Integer positionId;
	private Integer branchId;
	private String positionName;
	private String branchName;
	private Long leaveDaysDay;
	private BigDecimal leaveDaysHour;
	
	public Long getLeaveDaysDay() {
		this.leaveDaysDay=(long) Math.floor(leaveDays.doubleValue());
		return leaveDaysDay;
	}
	public void setLeaveDaysDay(Long leaveDaysDay) {
		this.leaveDaysDay = leaveDaysDay;
	}
	public BigDecimal getLeaveDaysHour() {
		this.leaveDaysHour=new BigDecimal(leaveDays.doubleValue()*8%8);
		if(leaveDaysHour.equals(new BigDecimal(0))){
			this.leaveDaysHour=null;
		}
		return leaveDaysHour;
	}
	public void setLeaveDaysHour(BigDecimal leaveDaysHour) {
		this.leaveDaysHour = leaveDaysHour;
	}
	/////////////////////////getter and setter //////////////////////////
	public Long getLeaveId() {
		return this.leaveId;
	}
	public void setLeaveId(Long value) {
		this.leaveId = value;
	}
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	public Long getLeaveType() {
		return this.leaveType;
	}
	public void setLeaveType(Long value) {
		this.leaveType = value;
	}
	public String getLeaveSubject() {
		return this.leaveSubject;
	}
	public void setLeaveSubject(String value) {
		this.leaveSubject = value;
	}
	public Date getLeaveDateBegin() {
		return this.leaveDateBegin;
	}
	public void setLeaveDateBegin(Date value) {
		this.leaveDateBegin = value;
	}
	public Date getLeaveDateEnd() {
		return this.leaveDateEnd;
	}
	public void setLeaveDateEnd(Date value) {
		this.leaveDateEnd = value;
	}
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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
	//////////////////////////////////////////////////////
	public LeaveVO(){}

	public LeaveVO(Leave leave){
		BeanUtils.copyProperties(leave,this);
	}
	public void copyValueTo(Leave bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Leave cloneBO(){
		Leave bo = new Leave();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Leave> cloneBOList(List<LeaveVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Leave>(0);
		List<Leave> result = new ArrayList<Leave>(vos.size());
		for(LeaveVO vo: vos){
			Leave bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
	public BigDecimal getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(BigDecimal leaveDays) {
		this.leaveDays = leaveDays;
	}
}