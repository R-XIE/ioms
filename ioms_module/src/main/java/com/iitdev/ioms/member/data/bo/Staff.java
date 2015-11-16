package com.iitdev.ioms.member.data.bo;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Staff
 */
@Table(name = "m_staff")
public class Staff implements Serializable{
	private static final long serialVersionUID = 1L;
	public Staff(){}
	//属性
	private Long staffId;//用户ID
	private String staffCode;//用户编号(与打卡机结合)
	private String staffLoginName;//用户登录名
	private String staffRealName;//用户真实名
	private String staffIcon;//头像
	private String staffPwd;//用户密码
	private Long staffSex;//性别
	private Long postId;//岗位
	private Long positionId;//职位
	private Long roleId;//角色
	private String staffEmail;//用户邮箱
	private String staffIdCard;//用户身份证号
	private String staffPhone;//用户联系方式
	private String staffEmergency;//紧急联系人和方式
	private String staffAddress;//地址
	private Long staffDegree;//学历
	private String staffGraduation;//毕业学校
	private String staffMajor;//专业
	private Date staffMajorDate;//毕业时间
	private Date staffEntryDate;//入职时间
	private Date staffContractDate;//签到合同日期
	private Date createTime;//注册时间
	private Long staffState;//用户状态
	private String leaveDate;//离职时间
	private String leaveRemark;//离职原因
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", unique = true, nullable = false,length = 10)
	public Long getStaffId() {
		return this.staffId;
	}
	
	public void setStaffId(Long StaffId) {
		this.staffId = StaffId;
	}
	
	@Column(name = "staff_code",unique = false,nullable = false,length = 255)
	public String getStaffCode() {
		return this.staffCode;
	}
	
	public void setStaffCode(String value) {
		this.staffCode = value;
	}
	
	@Column(name = "staff_login_name",unique = false,nullable = true,length = 255)
	public String getStaffLoginName() {
		return this.staffLoginName;
	}
	
	public void setStaffLoginName(String value) {
		this.staffLoginName = value;
	}
	
	@Column(name = "staff_real_name",unique = false,nullable = true,length = 255)
	public String getStaffRealName() {
		return this.staffRealName;
	}
	
	public void setStaffRealName(String value) {
		this.staffRealName = value;
	}
	
	@Column(name = "staff_icon",unique = false,nullable = true,length = 255)
	public String getStaffIcon() {
		return staffIcon;
	}

	public void setStaffIcon(String staffIcon) {
		this.staffIcon = staffIcon;
	}

	@Column(name = "staff_pwd",unique = false,nullable = true,length = 255)
	public String getStaffPwd() {
		return this.staffPwd;
	}
	
	public void setStaffPwd(String value) {
		this.staffPwd = value;
	}
	
	@Column(name = "staff_sex",unique = false,nullable = true,length = 10)
	public Long getStaffSex() {
		return this.staffSex;
	}
	
	public void setStaffSex(Long value) {
		this.staffSex = value;
	}
	
	@Column(name = "post_id",unique = false,nullable = true,length = 10)
	public Long getPostId() {
		return this.postId;
	}
	
	public void setPostId(Long value) {
		this.postId = value;
	}
	
	@Column(name = "position_id",unique = false,nullable = true,length = 10)
	public Long getPositionId() {
		return this.positionId;
	}
	
	public void setPositionId(Long value) {
		this.positionId = value;
	}
	
	@Column(name = "role_id",unique = false,nullable = true,length = 10)
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	
	@Column(name = "staff_email",unique = false,nullable = true,length = 255)
	public String getStaffEmail() {
		return this.staffEmail;
	}
	
	public void setStaffEmail(String value) {
		this.staffEmail = value;
	}
	
	@Column(name = "staff_id_card",unique = false,nullable = true,length = 255)
	public String getStaffIdCard() {
		return this.staffIdCard;
	}
	
	public void setStaffIdCard(String value) {
		this.staffIdCard = value;
	}
	
	@Column(name = "staff_phone",unique = false,nullable = true,length = 255)
	public String getStaffPhone() {
		return this.staffPhone;
	}
	
	public void setStaffPhone(String value) {
		this.staffPhone = value;
	}
	
	@Column(name = "staff_emergency",unique = false,nullable = true,length = 255)
	public String getStaffEmergency() {
		return this.staffEmergency;
	}
	
	public void setStaffEmergency(String value) {
		this.staffEmergency = value;
	}
	
	@Column(name = "staff_address",unique = false,nullable = true,length = 255)
	public String getStaffAddress() {
		return this.staffAddress;
	}
	
	public void setStaffAddress(String value) {
		this.staffAddress = value;
	}
	
	@Column(name = "staff_degree",unique = false,nullable = true,length = 10)
	public Long getStaffDegree() {
		return this.staffDegree;
	}
	
	public void setStaffDegree(Long value) {
		this.staffDegree = value;
	}
	
	@Column(name = "staff_graduation",unique = false,nullable = true,length = 255)
	public String getStaffGraduation() {
		return this.staffGraduation;
	}
	
	public void setStaffGraduation(String value) {
		this.staffGraduation = value;
	}
	
	@Column(name = "staff_major",unique = false,nullable = true,length = 255)
	public String getStaffMajor() {
		return this.staffMajor;
	}
	
	public void setStaffMajor(String value) {
		this.staffMajor = value;
	}
	
	@Column(name = "staff_major_date",unique = false,nullable = true,length = 0)
	public Date getStaffMajorDate() {
		return this.staffMajorDate;
	}
	
	public void setStaffMajorDate(Date value) {
		this.staffMajorDate = value;
	}
	
	@Column(name = "staff_entry_date",unique = false,nullable = true,length = 0)
	public Date getStaffEntryDate() {
		return this.staffEntryDate;
	}
	
	public void setStaffEntryDate(Date value) {
		this.staffEntryDate = value;
	}
	
	@Column(name = "staff_contract_date",unique = false,nullable = true,length = 0)
	public Date getStaffContractDate() {
		return this.staffContractDate;
	}
	
	public void setStaffContractDate(Date value) {
		this.staffContractDate = value;
	}
	
	@Column(name = "create_time",unique = false,nullable = true,length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	@Column(name = "staff_state",unique = false,nullable = true,length = 10)
	public Long getStaffState() {
		return this.staffState;
	}
	
	public void setStaffState(Long value) {
		this.staffState = value;
	}
	
	@Column(name = "leave_date",unique = false,nullable = true,length = 255)
	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	@Column(name = "leave_remark",unique = false,nullable = true,length = 255)
	public String getLeaveRemark() {
		return leaveRemark;
	}

	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}
	
}