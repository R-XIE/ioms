package com.iitdev.ioms.operation.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.base.data.vo.BranchVO;
import com.iitdev.ioms.base.service.BranchBS;
import com.iitdev.ioms.operation.common.Globals;
import com.iitdev.ioms.operation.data.bo.Pwd;
import com.iitdev.ioms.operation.data.vo.PwdStaffVO;
import com.iitdev.ioms.operation.data.vo.PwdVO;
import com.iitdev.ioms.operation.service.PwdBS;
import com.iitdev.ioms.operation.service.PwdStaffBS;

/**
 * 
 * 敏感信息 Action
 * 
 */
@Controller
@RequestMapping("/operation")
public class PwdAction extends BaseAction<PwdVO> {
	@Autowired
	private PwdBS pwdBS;
	@Autowired
	private PwdStaffBS pwdStaffBS;
	@Autowired
	private BranchBS branchBS;
	public PwdAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("pwdActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("operation/pwdList");
		List<PwdVO> pwdList = pwdBS.queryVOListAll();
		view.addObject("list", pwdList);
		view = setGlobals(view, "pwdActionList");
		return view;

	}
	
	@RequestMapping("pwdActionAuth")
	public ModelAndView auth(Long recordId) {
		ModelAndView view = new ModelAndView("operation/pwdAuth");
		List<BranchVO> branchList = branchBS.queryVOAllList();
		PwdVO vo = pwdBS.queryVOById(recordId);
		view.addObject("branchList", branchList);
		view.addObject("pwdVO", vo);
		return view;

	}
	
	@RequestMapping("pwdActionPermiss")
	public void permiss(PwdStaffVO pwdStaff,Boolean checked){
		try {
			if(checked){
				//如果授权则添加
				pwdStaffBS.addPwdStaff(pwdStaff.cloneBO());
			}else{
				pwdStaffBS.delPwdStaffByPwdStaff(pwdStaff.getPwdId(), pwdStaff.getStaffId());
			}
		} catch (Exception e) {
			super.printJson(false + "");
		}
		super.printJson(true + "");
	}
	
	@RequestMapping("pwdActionDel")
	public void del(Long recordId) throws Exception {
		PwdVO vo = pwdBS.queryVOById(recordId);
		boolean res = pwdBS.delPwd(vo.cloneBO());
		super.printJson(res + "");
	}

	@RequestMapping("pwdActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/pwdInput");
		String type = "";
		if (recordId != null) {
			PwdVO vo = pwdBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "EDIT";
		} else {
			type = "ADD";
		}
		view = setGlobals(view, "pwdActionInput");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("pwdActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/pwdInput");
		String type = "";
		if (recordId != null) {
			PwdVO vo = pwdBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "VIEW";
		}
		view = setGlobals(view, "pwdActionView");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("pwdActionSave")
	public void save(PwdVO pwd) throws Exception {
		Pwd temp;
		if (pwd.getPwdId() == null) {
			temp = pwdBS.addPwd(pwd.cloneBO());
		} else {
			temp = pwdBS.modifyPwd(pwd.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	public ModelAndView setGlobals(ModelAndView model, String url) {
		model.addObject("url", request.getContextPath() + "/operation/" + url
				+ ".htm");
		model.addObject("pwdLeaveMap", Globals.M_PWD_LEVEL);
		return model;
	}

}