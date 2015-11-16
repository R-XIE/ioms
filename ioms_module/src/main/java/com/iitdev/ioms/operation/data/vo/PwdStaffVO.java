package com.iitdev.ioms.operation.data.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.iitdev.ioms.operation.data.bo.PwdStaff;

/**
 * PwdStaff
 *
 */
public class PwdStaffVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from o_pwd_staff obj" ;
	private Long pwdStaffId;//pwdStaffId
	private Long pwdId;//敏感信息ID
	private Long staffId;//用户ID
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getPwdStaffId() {
		return this.pwdStaffId;
	}
	public void setPwdStaffId(Long value) {
		this.pwdStaffId = value;
	}
	public Long getPwdId() {
		return this.pwdId;
	}
	public void setPwdId(Long value) {
		this.pwdId = value;
	}
	public Long getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Long value) {
		this.staffId = value;
	}
	
	
	//////////////////////////////////////////////////////
	public PwdStaffVO(){}

	public PwdStaffVO(PwdStaff pwdStaff){
		BeanUtils.copyProperties(pwdStaff,this);
	}
	public void copyValueTo(PwdStaff bo){
		BeanUtils.copyProperties(this,bo);
	}
	public PwdStaff cloneBO(){
		PwdStaff bo = new PwdStaff();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<PwdStaff> cloneBOList(List<PwdStaffVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<PwdStaff>(0);
		List<PwdStaff> result = new ArrayList<PwdStaff>(vos.size());
		for(PwdStaffVO vo: vos){
			PwdStaff bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}