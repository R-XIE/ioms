package com.iitdev.ioms.operation.data.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.iitdev.ioms.operation.data.bo.Pwd;

public class PwdVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String QUERY_SQL = "select obj.* from o_pwd obj" ;
	private Long pwdId;
	private String pwdName;
	private String pwdLocation;
	private String pwdRemark;
	private Long pwdLevel;
	
	public Long getPwdId() {
		return pwdId;
	}
	public void setPwdId(Long pwdId) {
		this.pwdId = pwdId;
	}
	public String getPwdName() {
		return pwdName;
	}
	public void setPwdName(String pwdName) {
		this.pwdName = pwdName;
	}
	public String getPwdLocation() {
		return pwdLocation;
	}
	public void setPwdLocation(String pwdLocation) {
		this.pwdLocation = pwdLocation;
	}
	public String getPwdRemark() {
		return pwdRemark;
	}
	public void setPwdRemark(String pwdRemark) {
		this.pwdRemark = pwdRemark;
	}
	
	public Long getPwdLevel() {
		return pwdLevel;
	}
	public void setPwdLevel(Long pwdLevel) {
		this.pwdLevel = pwdLevel;
	}
	public PwdVO(){}
	public PwdVO(PwdVO Pwd){
		BeanUtils.copyProperties(Pwd,this);
	}
	public void copyValueTo(Pwd bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Pwd cloneBO(){
		Pwd bo = new Pwd();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Pwd> cloneBOList(List<PwdVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Pwd>(0);
		List<Pwd> result = new ArrayList<Pwd>(vos.size());
		for(PwdVO vo: vos){
			Pwd bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}