package com.iitdev.ioms.permiss.data.bo;
import java.io.Serializable;
import javax.persistence.*;


/**
 * Role
 */
@Table(name = "p_role")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	public Role(){}
	//属性
	private Long roleId;//角色ID
	private String roleName;//角色名称
	private String roleDescription;//角色描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false,length = 10)
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long RoleId) {
		this.roleId = RoleId;
	}
	
	@Column(name = "role_name",unique = false,nullable = true,length = 255)
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	@Column(name = "role_description",unique = false,nullable = true,length = 255)
	public String getRoleDescription() {
		return this.roleDescription;
	}
	
	public void setRoleDescription(String value) {
		this.roleDescription = value;
	}
	
	
	
}
