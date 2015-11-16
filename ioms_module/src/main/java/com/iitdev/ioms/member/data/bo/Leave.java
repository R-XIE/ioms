package com.iitdev.ioms.member.data.bo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Leave
 */
@Table(name = "m_leave")
public class Leave implements Serializable{
	private static final long serialVersionUID = 1L;
	public Leave(){}
	//属性
	private Long leaveId;//主键ID
	private Long staffId;//请假人
	private Long leaveType;//请假类别[事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假]
	private String leaveSubject;//请假事由
	private Date leaveDateBegin;//请假开始时间
	private Date leaveDateEnd;//请假结束时间(自动计算共计x天y时)
	private BigDecimal leaveDays;
	private Date createDate;//填写时间
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_id", unique = true, nullable = false,length = 10)
	public Long getLeaveId() {
		return this.leaveId;
	}
	
	public void setLeaveId(Long LeaveId) {
		this.leaveId = LeaveId;
	}
	
	
	@Column(name = "staff_id",unique = false,nullable = false,length = 10)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	
	@Column(name = "leave_type",unique = false,nullable = false,length = 10)
	public Long getLeaveType() {
		return this.leaveType;
	}
	
	public void setLeaveType(Long value) {
		this.leaveType = value;
	}
	
	@Column(name = "leave_subject",unique = false,nullable = false,length = 100)
	public String getLeaveSubject() {
		return this.leaveSubject;
	}
	
	public void setLeaveSubject(String value) {
		this.leaveSubject = value;
	}
	
	@Column(name = "leave_date_begin",unique = false,nullable = false,length = 0)
	public Date getLeaveDateBegin() {
		return this.leaveDateBegin;
	}
	
	public void setLeaveDateBegin(Date value) {
		this.leaveDateBegin = value;
	}
	
	@Column(name = "leave_date_end",unique = false,nullable = false,length = 0)
	public Date getLeaveDateEnd() {
		return this.leaveDateEnd;
	}
	
	public void setLeaveDateEnd(Date value) {
		this.leaveDateEnd = value;
	}
	
	@Column(name = "create_date",unique = false,nullable = false,length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	@Column(name = "leave_days",unique = false,nullable = false,length = 10)
	public BigDecimal getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(BigDecimal leaveDays) {
		this.leaveDays = leaveDays;
	}
	
	
}
