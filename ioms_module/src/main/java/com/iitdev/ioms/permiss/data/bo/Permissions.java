package com.iitdev.ioms.permiss.data.bo;
import java.io.Serializable;
import javax.persistence.*;


/**
 * Permissions
 */
@Table(name = "p_permissions")
public class Permissions implements Serializable{
	private static final long serialVersionUID = 1L;
	public Permissions(){}
	//属性
	private Long permissionsId;//权限ID
	private Long roleId;//角色ID
	private Long resourcesId;//资源ID
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permissions_id", unique = true, nullable = false,length = 10)
	public Long getPermissionsId() {
		return this.permissionsId;
	}
	
	public void setPermissionsId(Long PermissionsId) {
		this.permissionsId = PermissionsId;
	}
	
	@Column(name = "role_id",unique = false,nullable = true,length = 10)
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	
	@Column(name = "resources_id",unique = false,nullable = true,length = 10)
	public Long getResourcesId() {
		return this.resourcesId;
	}
	
	public void setResourcesId(Long value) {
		this.resourcesId = value;
	}
	
	
	
}
