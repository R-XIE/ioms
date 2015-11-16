package com.iitdev.ioms.member.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.member.common.CmdUtils;
import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.SalaryAlter;
import com.iitdev.ioms.member.data.vo.SalaryAlterVO;
import com.iitdev.ioms.member.service.SalaryAlterBS;

/**
 * 
 * 薪资调整 Action
 * 
 */
@Controller
@RequestMapping("/member")
public class SalaryAlterAction extends BaseAction<SalaryAlter> {
	@Autowired
	private SalaryAlterBS salaryAlterBS;
	
	public SalaryAlterAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("salaryAlterActionList")
	public ModelAndView list(String t) {
		ModelAndView view = new ModelAndView("member/salaryAlterList");
		List<SalaryAlterVO> leaveList= salaryAlterBS.queryVOAllList();
		view.addObject("list", leaveList);
		view=setGlobals(view);
		return view;

	}
	@RequestMapping("salaryAlterActionDel")
	public void del(Long recordId) throws Exception {
		SalaryAlterVO vo = salaryAlterBS.queryVOById(recordId);
		boolean res = salaryAlterBS.delSalaryAlter(vo.cloneBO());
		super.printJson(res+"");
	}
	
	@RequestMapping("salaryAlterActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/salaryAlterInput");
		String type="";
		if(recordId!=null){
			SalaryAlterVO vo = salaryAlterBS.queryVOById(recordId);
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
	
	@RequestMapping("salaryAlterActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/salaryAlterInput");
		String type="";
		if(recordId!=null){
			SalaryAlterVO vo = salaryAlterBS.queryVOById(recordId);
			//vo=numberFormat(vo);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view);
		view.addObject("type", type);
		return view;
	}
	/**
	 * 验证异动信息是否存在
	 * @param staffId
	 * @param alterDate
	 * @param alterId
	 */
	@RequestMapping("salaryAlterActionValidate")
	public void validateSalary(Long staffId,String alterDate,Long alterId){
		super.printJson(salaryAlterBS.validateBeanExsit(staffId,alterDate,alterId)+"");
	}
	@RequestMapping("salaryAlterActionSave")
	public void save(SalaryAlterVO salaryAlter) throws Exception {
		SalaryAlter temp;
		if (salaryAlter.getAlterId()== null) {
			salaryAlter.setAlterCreateDate(new Date());
			temp = salaryAlterBS.addSalaryAlter(salaryAlter.cloneBO());
		}else{
			salaryAlter.setAlterCreateDate(salaryAlterBS.queryVOById(salaryAlter.getAlterId()).getAlterCreateDate());
			temp =salaryAlterBS.modifySalaryAlter(salaryAlter.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public static ModelAndView setGlobals(ModelAndView model) {
		Map<Long, String> staffMap = CmdUtils.getStaffMap();
		Map<Long, String> alterMap = Globals.M_ALTER_CAUSE;
		model.addObject("staffMap", staffMap);
		model.addObject("alterMap", alterMap);
		return model;
	}
}