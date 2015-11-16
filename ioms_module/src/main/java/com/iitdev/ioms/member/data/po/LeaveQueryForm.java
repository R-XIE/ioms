package com.iitdev.ioms.member.data.po;


import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * LeaveListSearch
 *
 */
public class LeaveQueryForm extends QueryFormBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String staffName;// 请假名
	private Long branchId;// 部门
	private Long positionId;// 岗位
	private Long leaveType;// 请假类别[事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假]
	private String leaveDate;//
	private String leaveDays;// leaveDays
	private String createDateBE;

	// ///////////////////////getter and setter //////////////////////////
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Long leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}

	// ////////////////////////////////////////////////////
	public LeaveQueryForm() {
	}
	public String getCreateDateBE() {
		return createDateBE;
	}
	public void setCreateDateBE(String createDateBE) {
		this.createDateBE = createDateBE;
	}

}