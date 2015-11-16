package com.iitdev.ioms.permiss.data.vo;
import com.iitdev.ioms.permiss.data.bo.Permissions;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Permissions
 *
 */
public class PermissionsVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from p_permissions obj" ;
	private Long permissionsId;//权限ID
	private Long roleId;//角色ID
	private Long resourcesId;//资源ID
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getPermissionsId() {
		return this.permissionsId;
	}
	public void setPermissionsId(Long value) {
		this.permissionsId = value;
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	public Long getResourcesId() {
		return this.resourcesId;
	}
	public void setResourcesId(Long value) {
		this.resourcesId = value;
	}
	
	
	//////////////////////////////////////////////////////
	public PermissionsVO(){}

	public PermissionsVO(Permissions permissions){
		BeanUtils.copyProperties(permissions,this);
	}
	public void copyValueTo(Permissions bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Permissions cloneBO(){
		Permissions bo = new Permissions();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Permissions> cloneBOList(List<PermissionsVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Permissions>(0);
		List<Permissions> result = new ArrayList<Permissions>(vos.size());
		for(PermissionsVO vo: vos){
			Permissions bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}