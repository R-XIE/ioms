package com.iitdev.ioms.member.action;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.Leave;
import com.iitdev.ioms.member.data.po.LeaveQueryForm;
import com.iitdev.ioms.member.data.vo.LeaveVO;
import com.iitdev.ioms.member.service.LeaveBS;
import com.iitdev.ioms.permiss.data.bo.Role;
import com.iitdev.page.Result;

/**
 * 
 * 请假 Action
 * 
 */
@Controller
@RequestMapping("/member")
public class LeaveAction extends BaseAction<Role> {
	@Autowired
	private LeaveBS leaveBS;
	
	public LeaveAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/
	@RequestMapping("leaveActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("leave") LeaveQueryForm leave) {
		ModelAndView view = new ModelAndView("member/leaveListAjax");
		Map<String,String> queryMap=leave.buildQueryMap();
		Result<LeaveVO>  page=leaveBS.queryResultByPage(leave.getP(), queryMap, leave.getS());
		view.addObject("query", leave);
		view.addObject("page", page);
		view = setGlobals(view);
		return view;
	}
	
	@RequestMapping("leaveActionDel")
	public void del(Long recordId) throws Exception {
		LeaveVO vo = leaveBS.queryVOById(recordId);
		boolean res = leaveBS.delLeave(vo.cloneBO());
		super.printJson(res+"");
	}
	
	@RequestMapping("leaveActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/leaveInput");
		String type="";
		if(recordId!=null){
			LeaveVO vo = leaveBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("leaveActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/leaveInput");
		String type="";
		if(recordId!=null){
			LeaveVO vo = leaveBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("leaveActionSave")
	public void save(LeaveVO leave) throws Exception {
		Leave temp;
		if (leave.getLeaveId() == null) {
			leave.setCreateDate(new Date());
			temp = leaveBS.addLeave(leave.cloneBO());
		}else{
			LeaveVO vo = leaveBS.queryVOById(leave.getLeaveId());
			leave.setCreateDate(vo.getCreateDate());
			temp =leaveBS.modifyLeave(leave.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public static ModelAndView setGlobals(ModelAndView model) {
		Map<Long, String> positionMap = CmdUtils.getPositionMap();
		Map<Long, String> branchMap = CmdUtils.getBranchMap();
		Map<Long, String> staffMap = com.iitdev.ioms.member.common.CmdUtils.getStaffMap();
		Map<Long, String> leaveMap = Globals.M_LAEAVE_TYPE;
		model.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		model.addObject("branchMap", branchMap);
		model.addObject("positionMap", positionMap);
		model.addObject("staffMap", staffMap);
		model.addObject("leaveMap", leaveMap);
		return model;
	}
}