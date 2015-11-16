package com.iitdev.ioms.permiss.data.vo;
import com.iitdev.ioms.permiss.data.bo.Role;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Role
 *
 */
public class RoleVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from p_role obj" ;
	private Long roleId;//角色ID
	private String roleName;//角色名称
	private String roleDescription;//角色描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleName(String value) {
		this.roleName = value;
	}
	public String getRoleDescription() {
		return this.roleDescription;
	}
	public void setRoleDescription(String value) {
		this.roleDescription = value;
	}
	
	
	//////////////////////////////////////////////////////
	public RoleVO(){}

	public RoleVO(Role role){
		BeanUtils.copyProperties(role,this);
	}
	public void copyValueTo(Role bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Role cloneBO(){
		Role bo = new Role();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Role> cloneBOList(List<RoleVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Role>(0);
		List<Role> result = new ArrayList<Role>(vos.size());
		for(RoleVO vo: vos){
			Role bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}