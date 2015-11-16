package com.iitdev.ioms.member.action;


import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.member.common.CmdUtils;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.member.data.bo.Salary;
import com.iitdev.ioms.member.data.po.SalaryQueryForm;
import com.iitdev.ioms.member.data.vo.SalaryVO;
import com.iitdev.ioms.member.service.LeaveBS;
import com.iitdev.ioms.member.service.SalaryBS;
import com.iitdev.page.Result;
import com.iitdev.web.GsonTools;
/**
*
*工资条 Action 
*
*/
@Controller
@RequestMapping("/member")
public class SalaryAction extends BaseAction<SalaryVO>{
	public  SalaryAction(){}
	@Autowired
	private SalaryBS salaryBS;
	@Autowired
	private LeaveBS leaveBS;
	/**************************************页面跳转******************************/
	//TO 查看界面
	@RequestMapping("salaryActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("salary") SalaryQueryForm salary) {
		ModelAndView view = new ModelAndView("member/salaryListAjax");
		//
		Map<String,String> queryMap=salary.buildQueryMap();
		Result<SalaryVO>  page=salaryBS.queryResultByPage(salary.getP(), queryMap, salary.getS());
		view.addObject("query", salary);
		view.addObject("page", page);
		view=setGlobals(view,"salaryActionListAjax");
		return view;
	}
	
	@RequestMapping("salaryActionDel")
	public void del(Long recordId) throws Exception {
		SalaryVO vo = salaryBS.queryVOById(recordId);
		boolean res = salaryBS.delSalary(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("salaryActionInput")
	public ModelAndView input(Long recordId,String months) throws Exception {
		ModelAndView view = new ModelAndView("member/salaryInput");
		String type="";
		if(recordId!=null){
			SalaryVO vo = salaryBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			Long staffId=new Long(CmdUtils.getStaffIdDefalt());
			BigDecimal none=leaveBS.validateListByStaffMonth(staffId,months);
			//获取应出勤
			BigDecimal attendance=salaryBS.querySalaryAttendance(months);
			BigDecimal factAttendance=salaryBS.querySalaryAttendance(months).subtract(none);
			//获取上个月的信息
			SalaryVO vo =salaryBS.validateBeanStaff(staffId, CmdUtils.getLastMonth(months),months);
			view.addObject("last", vo);
			view.addObject("factAttendance", factAttendance);
			view.addObject("attendance", attendance);
			type="ADD";
		}
		view=setGlobals(view,"salaryActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("salaryActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/salaryInput");
		String type="";
		if(recordId!=null){
			SalaryVO vo= salaryBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"salaryActionView");
		view.addObject("type", type);
		return view;
	}
	
	
	
	@RequestMapping("salaryActionSave")
	public void save(SalaryVO salaryVO) throws Exception {
		Salary temp;
		if (salaryVO.getSalaryId() == null) {
			temp = salaryBS.addSalary(salaryVO.cloneBO());
		}else{
			temp =salaryBS.modifySalary(salaryVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	/**
	 * 验证工资是否被录入
	 * @param staffId
	 * @param salaryDateMonths
	 * @param salaryId
	 */
	@RequestMapping("salaryActionValidate")
	public void validateSalary(Long staffId,String salaryDateMonths,Long salaryId){
		super.printJson(salaryBS.validateBeanExsit(staffId, salaryDateMonths,salaryId)+"");
	}
	
	/**
	 * 计算实际出勤
	 * @param staffId
	 * @param salary
	 * @param overtime
	 * @param months
	 */
	@RequestMapping("salaryActionSalaryFact")
	public void salaryFact(Long staffId,BigDecimal salary,BigDecimal overtime,String months ){
		if(overtime==null)overtime=new BigDecimal(0);
		BigDecimal factAttendance=salary.subtract(leaveBS.validateListByStaffMonth(staffId,months)).add(overtime);
		super.printJson(factAttendance.toString());
	}
	/**
	 * 获取上月的工资条
	 * @param staffId
	 * @param months
	 */
	@RequestMapping("salaryActionLastSalary")
	public void lastSalary(Long staffId,String months ){
		SalaryVO vo =salaryBS.validateBeanStaff(staffId, CmdUtils.getLastMonth(months),months);
		String json=GsonTools.bean2json(vo);//可能为空
		super.printJson(json);
	}
	public  ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("url", request.getContextPath()+"/member/"+url+".htm");
		model.addObject("staffMap", CmdUtils.getStaffMap());
		model.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		return model;
	}
}