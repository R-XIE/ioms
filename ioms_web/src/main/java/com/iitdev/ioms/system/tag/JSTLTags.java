package com.iitdev.ioms.system.tag;

import java.util.List;

import com.iitdev.globals.ResourcesPO;
import com.iitdev.globals.ResourcesUtils;
import com.iitdev.ioms.member.common.CmdUtils;
import com.iitdev.ioms.member.data.vo.StaffVO;

/**
 * 主要用户JSTL的方法调用
 * @author jerry
 *
 */
public class JSTLTags {

	public static List<StaffVO> getStaffByBranch(Integer branchId) {
		return CmdUtils.getStaffByBranch(Long.valueOf(branchId));
	}
	
	public static Boolean getPwdStaffAuth(Integer pwdId,Integer staffId){
		return com.iitdev.ioms.operation.common.CmdUtils.isAuth(Long.valueOf(pwdId), Long.valueOf(staffId));
	}
	
	public static List<ResourcesPO> getResourcesByModule(Integer moduleId){
		return ResourcesUtils.getResourcesByModule(Long.valueOf(moduleId));
	}
	
	public static Boolean getResourceRoleAuth(Integer resourceId,Integer roleId){
		return com.iitdev.ioms.permiss.common.CmdUtils.isAuth(Long.valueOf(resourceId), Long.valueOf(roleId));
	}
}
