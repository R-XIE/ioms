package com.iitdev.ioms.permiss.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.permiss.data.vo.PermissionsVO;
import com.iitdev.ioms.permiss.data.vo.RoleVO;
import com.iitdev.ioms.permiss.service.PermissionsBS;
import com.iitdev.ioms.permiss.service.RoleBS;
import com.iitdev.web.SpringGetBean;

public class CmdUtils {
	/**
	 * 获取所有的角色
	 * @return
	 */
	public static Map<Long,String> getRoleMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		RoleBS roleBS = (RoleBS) SpringGetBean.getBean("roleBS");
		List<RoleVO> roleList= roleBS.queryVOAllList();
		for (RoleVO roleVO : roleList) {
			map.put(roleVO.getRoleId(), roleVO.getRoleName());
		}
		return map;
	}
	
	
	/**
	 * 判断资源是否被授权
	 * @param resourceId
	 * @param roleId
	 * @return
	 */
	public static Boolean isAuth(Long resourceId,Long roleId){
		PermissionsBS permissionsBS = (PermissionsBS) SpringGetBean.getBean("permissionsBS");
		PermissionsVO vo=permissionsBS.permissVOByRoleRes(roleId, resourceId);
		if(vo!=null)
			return true;
		else 
			return false;
	}
}
