package com.iitdev.ioms.permiss.service;

//import com.iitdev.ioms.permiss.data.bo.Permissions;
import java.util.List;

import com.iitdev.globals.ResourcesPO;
import com.iitdev.ioms.permiss.data.bo.Permissions;
import com.iitdev.ioms.permiss.data.vo.PermissionsVO;
import com.iitdev.ioms.view.data.po.ModulePO;
import com.iitdev.orm.PublicBS;


/**
 *权限 SERVICE接口 
 */
public interface PermissionsBS extends PublicBS {
	/**通用持久化方法(添加和修改)***/
	public Permissions addPermissions(Permissions entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public void delPermissions(Permissions entity)throws Exception;
	
	/**
	 * 通过角色和资源定位权限VO
	 * @see RoleAction#permiss(Permissions, Boolean)
	 * @param roleId 角色Id
	 * @param resourceId 资源Id
	 * @return
	 */
	public PermissionsVO permissVOByRoleRes(Long roleId,Long resourceId);
	/**
	 * 通过用户获取拥有的模块.
	 * @see MainAction#left()
	 * @param loginInfo
	 * @return
	 */
	public List<ModulePO> permissModuleByStaff(Long staffId);
	
	/**
	 * 通过用户获取所有的资源
	 * @see UrlInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object)
	 * @param staffId
	 * @return
	 */
	public List<ResourcesPO> permissResourceByStaff(Long staffId);

}