package com.iitdev.ioms.operation.data.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "o_pwd")
public class Pwd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long pwdId;
	private String pwdName;
	private String pwdLocation;
	private String pwdRemark;
	private Long pwdLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pwd_id", unique = true, nullable = false,length = 10)
	public Long getPwdId() {
		return pwdId;
	}
	public void setPwdId(Long pwdId) {
		this.pwdId = pwdId;
	}
	
	@Column(name = "pwd_name",unique = false,nullable = true,length = 255)
	public String getPwdName() {
		return pwdName;
	}
	public void setPwdName(String pwdName) {
		this.pwdName = pwdName;
	}
	
	@Column(name = "pwd_location",unique = false,nullable = true,length = 255)
	public String getPwdLocation() {
		return pwdLocation;
	}
	public void setPwdLocation(String pwdLocation) {
		this.pwdLocation = pwdLocation;
	}
	
	@Column(name = "pwd_remark",unique = false,nullable = true,length = 255)
	public String getPwdRemark() {
		return pwdRemark;
	}
	public void setPwdRemark(String pwdRemark) {
		this.pwdRemark = pwdRemark;
	}
	
	@Column(name = "pwd_level",unique = false,nullable = false,length = 10)
	public Long getPwdLevel() {
		return pwdLevel;
	}
	public void setPwdLevel(Long pwdLevel) {
		this.pwdLevel = pwdLevel;
	}
}