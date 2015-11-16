package com.iitdev.ioms.operation.data.bo;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PwdStaff
 */
@Table(name = "o_pwd_staff")
public class PwdStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	public PwdStaff(){}
	//属性
	private Long pwdStaffId;//pwdStaffId
	private Long pwdId;//敏感信息ID
	private Long staffId;//用户ID
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pwd_staff_id", unique = true, nullable = false,length = 19)
	public Long getPwdStaffId() {
		return this.pwdStaffId;
	}
	
	public void setPwdStaffId(Long PwdStaffId) {
		this.pwdStaffId = PwdStaffId;
	}
	
	@Column(name = "pwd_id",unique = false,nullable = false,length = 19)
	public Long getPwdId() {
		return this.pwdId;
	}
	
	public void setPwdId(Long value) {
		this.pwdId = value;
	}
	
	@Column(name = "staff_id",unique = false,nullable = false,length = 19)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	
	
}
