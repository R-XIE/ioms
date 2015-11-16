package com.iitdev.ioms.permiss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.iitdev.globals.AnalysisConstant;
import com.iitdev.globals.ResourcesPO;
import com.iitdev.ioms.permiss.data.bo.Permissions;
import com.iitdev.ioms.permiss.data.vo.PermissionsVO;
import com.iitdev.ioms.permiss.service.PermissionsBS;
import com.iitdev.ioms.view.data.po.ModulePO;
import com.iitdev.orm.PublicBSImpl;

/**
 * 权限 SERVICE接口 实现类
 * 
 */
@Service("permissionsBS")
public class PermissionsBSImpl extends PublicBSImpl implements PermissionsBS {
	public final String SQL_QUERY_VO = PermissionsVO.QUERY_SQL;

	/** 有外键的字段必须填充,而且要一致 ***/
	public Permissions addPermissions(Permissions entity) throws Exception {
		// 1主表验证和此表的外键字段一致
		// 添加code编码
		// 保存表
		super.saveObject(entity);
		return entity;
	}

	public void delPermissions(Permissions entity) throws Exception {
		// 1表删除
		delete(entity);
	}

	public PermissionsVO permissVOByRoleRes(Long roleId, Long resourceId) {
		String sql ="select * from p_permissions permiss where permiss.role_id=? and permiss.resources_id=?";
		PermissionsVO vo = super.queryForBean(PermissionsVO.class, sql, new Object[]{roleId,resourceId});
		return vo;
	}
	
	@Override
	public List<ModulePO> permissModuleByStaff(Long staffId) {
		String sql ="select permissions.resources_id from m_staff staff  "
				+ "INNER JOIN p_permissions permissions on permissions.role_id=staff.role_id "
				+ "where staff.staff_state!=-1 and staff_id=? ORDER BY resources_id";
		List<Long> resourceKeys=super.queryForList(sql,
				new Object[]{staffId}, Long.class);
		List<ModulePO> module =new ArrayList<ModulePO>();
		for (Entry<Long, ResourcesPO> res : AnalysisConstant.allRes.entrySet()) {
			ResourcesPO resPO=res.getValue();
			if(resPO.getResourcesSuper().equals(new Long("-1"))){
				ModulePO moduleTemp=new ModulePO();
				moduleTemp.setModuleId(resPO.getResourcesId());
				moduleTemp.setModuleCss(resPO.getResourcesCss());
				moduleTemp.setModuleName(resPO.getResourcesName());
				module.add(moduleTemp);
			}
		}
		for (Long ilong : resourceKeys) {
			ResourcesPO po=AnalysisConstant.allRes.get(ilong);
			for (int i=0;i<module.size();i++) {
				if(po.getResourcesSuper().equals(module.get(i).getModuleId())){
					List<ResourcesPO> resList=module.get(i).getResources();
					if(resList==null){
						resList=new ArrayList<ResourcesPO>();
					}	
					resList.add(po);
					module.get(i).setResources(resList);
				}
			}
		}
		for (int i = 0; i < module.size(); i++) {
			List<ResourcesPO> res=module.get(i).getResources();
			if(res==null|| res.isEmpty()){
				module.remove(i);
				i--;
			}
		}
		return module;
	}
	
	

	@Override
	public List<ResourcesPO> permissResourceByStaff(Long staffId) {
		String sql ="select permissions.resources_id from m_staff staff  "
				+ "INNER JOIN p_permissions permissions on permissions.role_id=staff.role_id "
				+ "where staff.staff_state!=-1 and staff_id=?";
		List<Long> resourceKeys=super.queryForList(sql,
				new Object[]{staffId}, Long.class);
		List<ResourcesPO> resources=new ArrayList<ResourcesPO>();
		for (Long key : resourceKeys) {
			resources.add(AnalysisConstant.allRes.get(key));
		}
		return resources;
	}
	
}