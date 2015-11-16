package com.iitdev.ioms.permiss.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iitdev.orm.PublicBSImpl;
import com.iitdev.ioms.permiss.data.bo.Role;
import com.iitdev.ioms.permiss.data.vo.RoleVO;
import com.iitdev.ioms.permiss.service.RoleBS;


/**
 *角色 SERVICE接口 实现类
 *
 */
@Service("roleBS")
public class RoleBSImpl extends PublicBSImpl implements RoleBS{
	public  final String SQL_QUERY_VO =  RoleVO.QUERY_SQL;
	
	public RoleVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.role_id  = ? ";
		return super.queryForBean(RoleVO.class,sql ,new Object[]{id});
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Role addRole(Role entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Role modifyRole(Role entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public boolean delRole(Role entity)throws Exception{
		//1表删除之前判断该角色是否已经被使用
		int icount = super.queryForInt("select count(1) from m_staff staff where staff.role_id=?", new Object[]{entity.getRoleId()});
		if(icount!=0){
			return false;
		}else{
			super.executeSql(" delete from p_permissions where  role_id="+entity.getRoleId());
			delete(entity);
			return true;
		}
		
	}

	@Override
	public List<RoleVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(RoleVO.class,
				RoleVO.QUERY_SQL, new Object[] {});
	}

}