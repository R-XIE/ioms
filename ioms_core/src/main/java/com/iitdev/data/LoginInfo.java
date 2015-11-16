package com.iitdev.data;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	/**
	 * @param
	 * @return
	 * @throws
	 * @since
	 */
	private static final long serialVersionUID = -2003960141955329143L;

	private Long staffId;
	private String staffCode;
	private String staffLoginName;
	private String staffRealName;
	private String staffIcon;
	private Long staffSex;//性别
	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffLoginName() {
		return staffLoginName;
	}

	public void setStaffLoginName(String staffLoginName) {
		this.staffLoginName = staffLoginName;
	}

	public String getStaffRealName() {
		return staffRealName;
	}

	public void setStaffRealName(String staffRealName) {
		this.staffRealName = staffRealName;
	}

	public String getStaffIcon() {
		return staffIcon;
	}

	public void setStaffIcon(String staffIcon) {
		this.staffIcon = staffIcon;
	}

	public Long getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(Long staffSex) {
		this.staffSex = staffSex;
	}

}
