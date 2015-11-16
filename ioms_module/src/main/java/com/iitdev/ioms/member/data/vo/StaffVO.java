package com.iitdev.ioms.member.data.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.data.LoginInfo;
import com.iitdev.ioms.member.data.bo.Staff;

/**
 * Staff
 *
 */
public class StaffVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from m_staff obj  where obj.staff_state!=-1";
	public static final String QUERY_SQL_ALL = "select obj.*,branch.branch_name from m_staff obj "
			+ "INNER JOIN b_position position ON position.position_id=obj.position_id "
			+ "INNER JOIN b_branch branch on position.branch_id=branch.branch_id";
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date staffMajorDate;//毕业时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date staffEntryDate;//入职时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date staffContractDate;//签到合同日期
	private Date createTime;//注册时间
	private Long staffState;//用户状态
	private String leaveDate;//离职时间
	private String leaveRemark;//离职原因
	
	private String branchName;
	/////////////////////////getter and setter //////////////////////////
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	public String getStaffCode() {
		return this.staffCode;
	}
	public void setStaffCode(String value) {
		this.staffCode = value;
	}
	public String getStaffLoginName() {
		return this.staffLoginName;
	}
	public void setStaffLoginName(String value) {
		this.staffLoginName = value;
	}
	public String getStaffRealName() {
		return this.staffRealName;
	}
	public void setStaffRealName(String value) {
		this.staffRealName = value;
	}
	public String getStaffIcon() {
		return staffIcon;
	}
	public void setStaffIcon(String staffIcon) {
		this.staffIcon = staffIcon;
	}
	public String getStaffPwd() {
		return this.staffPwd;
	}
	public void setStaffPwd(String value) {
		this.staffPwd = value;
	}
	public Long getStaffSex() {
		return this.staffSex;
	}
	public void setStaffSex(Long value) {
		this.staffSex = value;
	}
	public Long getPostId() {
		return this.postId;
	}
	public void setPostId(Long value) {
		this.postId = value;
	}
	public Long getPositionId() {
		return this.positionId;
	}
	public void setPositionId(Long value) {
		this.positionId = value;
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	public String getStaffEmail() {
		return this.staffEmail;
	}
	public void setStaffEmail(String value) {
		this.staffEmail = value;
	}
	public String getStaffIdCard() {
		return this.staffIdCard;
	}
	public void setStaffIdCard(String value) {
		this.staffIdCard = value;
	}
	public String getStaffPhone() {
		return this.staffPhone;
	}
	public void setStaffPhone(String value) {
		this.staffPhone = value;
	}
	public String getStaffEmergency() {
		return this.staffEmergency;
	}
	public void setStaffEmergency(String value) {
		this.staffEmergency = value;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getLeaveRemark() {
		return leaveRemark;
	}
	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}
	public String getStaffAddress() {
		return this.staffAddress;
	}
	public void setStaffAddress(String value) {
		this.staffAddress = value;
	}
	public Long getStaffDegree() {
		return this.staffDegree;
	}
	public void setStaffDegree(Long value) {
		this.staffDegree = value;
	}
	public String getStaffGraduation() {
		return this.staffGraduation;
	}
	public void setStaffGraduation(String value) {
		this.staffGraduation = value;
	}
	public String getStaffMajor() {
		return this.staffMajor;
	}
	public void setStaffMajor(String value) {
		this.staffMajor = value;
	}
	public Date getStaffMajorDate() {
		return this.staffMajorDate;
	}
	public void setStaffMajorDate(Date value) {
		this.staffMajorDate = value;
	}
	public Date getStaffEntryDate() {
		return this.staffEntryDate;
	}
	public void setStaffEntryDate(Date value) {
		this.staffEntryDate = value;
	}
	public Date getStaffContractDate() {
		return this.staffContractDate;
	}
	public void setStaffContractDate(Date value) {
		this.staffContractDate = value;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	public Long getStaffState() {
		return this.staffState;
	}
	public void setStaffState(Long value) {
		this.staffState = value;
	}
	
	
	//////////////////////////////////////////////////////
	public StaffVO(){}

	public StaffVO(Staff staff){
		BeanUtils.copyProperties(staff,this);
	}
	public void copyValueTo(Staff bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Staff cloneBO(){
		Staff bo = new Staff();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Staff> cloneBOList(List<StaffVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Staff>(0);
		List<Staff> result = new ArrayList<Staff>(vos.size());
		for(StaffVO vo: vos){
			Staff bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
	
	public LoginInfo getLoginInfo(){
		LoginInfo loginInfo=new LoginInfo();
		loginInfo.setStaffId(staffId);
		loginInfo.setStaffCode(staffCode);
		loginInfo.setStaffIcon(staffIcon);
		loginInfo.setStaffLoginName(staffLoginName);
		loginInfo.setStaffRealName(staffRealName);
		loginInfo.setStaffSex(staffSex);
		return loginInfo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}