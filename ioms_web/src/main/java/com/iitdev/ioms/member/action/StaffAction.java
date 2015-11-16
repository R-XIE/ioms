package com.iitdev.ioms.member.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.encrypt.MD5;
import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.Staff;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.ioms.permiss.data.bo.Role;

/**
 * 
 * 职位 Action
 * 
 */
@Controller
@RequestMapping("/member")
public class StaffAction extends BaseAction<Role> {
	@Autowired
	private StaffBS staffBS;
	
	public StaffAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("staffActionList")
	public ModelAndView list(Boolean isLeave) {
		ModelAndView view = new ModelAndView("member/staffList");
		if(isLeave==null)isLeave=false;
		List<StaffVO> postList= staffBS.queryVOAllList(isLeave);
		view.addObject("list", postList);
		view.addObject("pageStatus", isLeave);
		view.addObject("url", request.getContextPath()+"/member/staffActionList.htm?isLeave="+isLeave);
		view=setGlobals(view);
		view.addObject("loginInfoId",super.getLoginInfo().getStaffId());
		return view;

	}
	@RequestMapping("staffActionDel")
	public void del(Long recordId) throws Exception {
		StaffVO vo = staffBS.queryVOById(recordId);
		boolean res = staffBS.delStaff(vo.cloneBO());
		super.printJson(res + "");
	}
	
	/**
	 * 验证用户名是否重复
	 * @param staffLoginName
	 * @throws Exception
	 */
	@RequestMapping("staffActionValidate")
	public void validate(String staffLoginName) throws Exception {
		boolean res = staffBS.validateInputLoginName(staffLoginName);
		super.printJson(res + "");
	}
	
	@RequestMapping("staffActionInput")
	public ModelAndView input(Long recordId,Boolean isLeave) throws Exception {
		ModelAndView view = new ModelAndView("member/staffInput");
		String type="";
		if(recordId!=null){
			StaffVO vo = staffBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view.addObject("type", type);
		view=setGlobals(view);
		if(isLeave==null||isLeave==false)
			view.addObject("staffMap", Globals.M_MEMBER_STATE_UNLEAVE);
		return view;
	}
	
	/**
	 * 离职操作
	 * @param id
	 * @param leaveDate
	 * @param leaveRemark
	 * @param isLeave
	 * @throws Exception
	 */
	@RequestMapping("staffActionLeave")
	public void leave(Long id,String leaveDate,String leaveRemark,Boolean isLeave) throws Exception{
		StaffVO staff= staffBS.queryVOById(id);
		if(isLeave==null||isLeave==false){
			//即将离职
			staff.setStaffState(-1l);
		}
		staff.setLeaveDate(leaveDate);
		staff.setLeaveRemark(leaveRemark);
		Staff bo= staffBS.modifyStaff(staff.cloneBO());
		if(bo!=null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	@RequestMapping("staffActionView")
	public ModelAndView view(Long recordId,Boolean isLeave) throws Exception {
		ModelAndView view = new ModelAndView("member/staffInput");
		String type="";
		if(recordId!=null){
			StaffVO vo = staffBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view.addObject("type", type);
		view=setGlobals(view);
		if(isLeave!=null&&isLeave==true){
			view.addObject("pageStatus", isLeave);
		}
		return view;
	}
	
	@RequestMapping("staffActionSave")
	public void save(StaffVO staff) throws Exception {
		Staff temp;
		if (staff.getStaffId() == null) {
			staff.setCreateTime(new Date());
			staff.setStaffPwd(MD5.getMd5Password(staff.getStaffLoginName()+"123"));//初始密码为用户名123
//			staff.setStaffPwd("6e4e01e30ccff52ec112c1d301fa744d");//初始密码6个1
			temp = staffBS.addStaff(staff.cloneBO());
		}else{
			StaffVO vo = staffBS.queryVOById(staff.getStaffId());
			staff.setCreateTime(vo.getCreateTime());
			staff.setStaffIcon(vo.getStaffIcon());
			staff.setStaffPwd(vo.getStaffPwd());
			temp =staffBS.modifyStaff(staff.cloneBO());
			if(vo.getStaffId().equals(super.getLoginInfo().getStaffId())){
				super.setLoginInfo(staff.getLoginInfo());
			}
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	private ModelAndView setGlobals(ModelAndView model){
		Map<Long,String> positionMap=CmdUtils. getPositionMap();
		Map<Long,String> postMap=CmdUtils. getPostMap();
		Map<Long,String> roleMap=com.iitdev.ioms.permiss.common.CmdUtils.getRoleMap();
		Map<Long,String> staffMap=Globals.M_MEMBER_STATE;
		Map<Long,String> sexMap=Globals.M_MEMBER_SEX;
		Map<Long,String> degreeMap=Globals.M_MEMBER_DEGREE;
		model.addObject("positionMap", positionMap);
		model.addObject("postMap", postMap);
		model.addObject("staffMap", staffMap);
		model.addObject("sexMap", sexMap);
		model.addObject("roleMap", roleMap);
		model.addObject("degreeMap", degreeMap);
		return model;
	}

}