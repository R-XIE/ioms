package com.iitdev.ioms.member.data.po;
import com.iitdev.ioms.member.data.bo.Leave;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Leave
 *
 */
public class LeavePO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*  from v_leave obj ";
	
	private Long leaveId;//主键ID
	private Long staffId;//请假人
	private Long leaveType;//请假类别[事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假]
	private String leaveSubject;//请假事由
	private Date leaveDateBegin;//请假开始时间
	private Date leaveDateEnd;//请假结束时间(自动计算共计x天y时)
	private BigDecimal leaveDays;
	private Date createDate;//填写时间
	private String leaveDateMonths;
	
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
	
	//////////////////////////////////////////////////////
	public LeavePO(){}

	public LeavePO(Leave leave){
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
	
	public static List<Leave> cloneBOList(List<LeavePO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Leave>(0);
		List<Leave> result = new ArrayList<Leave>(vos.size());
		for(LeavePO vo: vos){
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
	public String getLeaveDateMonths() {
		return leaveDateMonths;
	}
	public void setLeaveDateMonths(String leaveDateMonths) {
		this.leaveDateMonths = leaveDateMonths;
	}
}