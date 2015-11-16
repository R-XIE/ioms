package com.iitdev.ioms.permiss.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.globals.ResourcesPO;
import com.iitdev.globals.ResourcesUtils;
import com.iitdev.ioms.permiss.data.bo.Permissions;
import com.iitdev.ioms.permiss.data.bo.Role;
import com.iitdev.ioms.permiss.data.vo.RoleVO;
import com.iitdev.ioms.permiss.service.PermissionsBS;
import com.iitdev.ioms.permiss.service.RoleBS;

/**
 * 
 * staff Action
 * 
 */
@Controller
@RequestMapping("/permiss")
public class RoleAction extends BaseAction<Role> {
	@Autowired
	private RoleBS roleBS;
	@Autowired
	private PermissionsBS permissionsBS;
	
	public RoleAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("roleActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("permiss/roleList");
		List<RoleVO> roleList = roleBS.queryVOAllList();
		view.addObject("list", roleList);
		return view;

	}
	
	@RequestMapping("roleActionAuth")
	public ModelAndView auth(Long recordId) {
		ModelAndView view = new ModelAndView("permiss/auth");
		RoleVO role= roleBS.queryVOById(recordId);
		List<ResourcesPO> reses=ResourcesUtils.getAllResources();
//		List<ModulePO> module = permissionsBS.permissRoleModule(role.getRoleId());
		view.addObject("module", reses);
		view.addObject("role", role);
		return view;
	}
	
	@RequestMapping("roleActionDel")
	public void del(Long recordId) throws Exception {
		RoleVO vo = roleBS.queryVOById(recordId);
		boolean res = roleBS.delRole(vo.cloneBO());
		super.printJson(res + "");
	}
	
	@RequestMapping("roleActionSave")
	public void save(Role role) throws Exception {
		Role temp;
		if (role.getRoleId() == null) {
			temp = roleBS.addRole(role);
		}else{
			temp =roleBS.modifyRole(role);
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	@RequestMapping("roleActionPermiss")
	public void permiss(Permissions permissions,Boolean checked){
		try {
			if(checked){
				//如果授权则添加
				permissionsBS.addPermissions(permissions);
			}else{
				//否则删除
				permissions=permissionsBS.permissVOByRoleRes(permissions.getRoleId(), permissions.getResourcesId()).cloneBO();
				permissionsBS.delPermissions(permissions);
			}
		} catch (Exception e) {
			super.printJson(false + "");
		}
		super.printJson(true + "");
	}
}