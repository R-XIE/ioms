package com.iitdev.ioms.permiss.service;

//import com.iitdev.ioms.permiss.data.bo.Role;

import java.util.List;

import com.iitdev.ioms.permiss.data.bo.Role;
import com.iitdev.ioms.permiss.data.vo.RoleVO;
import com.iitdev.orm.PublicBS;

/**
 *角色 SERVICE接口 
 */
public interface RoleBS extends PublicBS {
	/**通用查询方法***/
	public RoleVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Role addRole(Role entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Role modifyRole(Role entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delRole(Role entity)throws Exception;
	public List<RoleVO> queryVOAllList();
}