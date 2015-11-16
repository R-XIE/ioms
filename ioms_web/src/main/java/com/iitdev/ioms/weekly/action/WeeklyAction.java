package com.iitdev.ioms.weekly.action;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.member.common.CmdUtils;
import com.iitdev.ioms.weekly.common.Globals;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.weekly.data.bo.Weekly;
import com.iitdev.ioms.weekly.data.po.WeeklyQueryForm;
import com.iitdev.ioms.weekly.data.vo.WeeklyVO;
import com.iitdev.ioms.weekly.service.WeeklyBS;
import com.iitdev.page.Result;

/**
 * 
 * 个人周报 Action
 * 
 */
@Controller
@RequestMapping("/weekly")
public class WeeklyAction extends BaseAction<WeeklyVO> {
	public WeeklyAction() {
	}

	@Autowired
	private WeeklyBS weeklyBS;

	/************************************** 页面跳转 ******************************/
	@RequestMapping("weeklyActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("weekly") WeeklyQueryForm weekly) {
		ModelAndView view = new ModelAndView("weekly/weeklyListAjax");
		Map<String,String> queryMap=weekly.buildQueryMap();
		Result<WeeklyVO>  page=weeklyBS.queryResultByPage(weekly.getP(), queryMap, weekly.getS());
		view.addObject("query", weekly);
		view.addObject("page", page);
		view=setGlobals(view,"weeklyActionListAjax");
		return view;
	}
	
	@RequestMapping("weeklyActionDel")
	public void del(Long recordId) throws Exception {
		WeeklyVO vo = weeklyBS.queryVOById(recordId);
		boolean res = weeklyBS.delWeekly(vo.cloneBO());
		super.printJson(res + "");
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("weeklyActionValidate")
	public void validate(Long staffId,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
			throws Exception {
		boolean res = weeklyBS.validateWeeklyStaffMonth(staffId, beginDate, endDate);
		super.printJson(res + "");
	}

	@RequestMapping("weeklyActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("weekly/weeklyInput");
		String type = "";
		if (recordId != null) {
			WeeklyVO vo = weeklyBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "EDIT";
		} else {
			type = "ADD";
		}
		view = setGlobals(view, "weeklyActionInput");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("weeklyActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("weekly/weeklyInput");
		String type = "";
		if (recordId != null) {
			WeeklyVO vo = weeklyBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "VIEW";
		}
		view = setGlobals(view, "weeklyActionView");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("weeklyActionSave")
	public void save(WeeklyVO weeklyVO) throws Exception {
		Weekly temp;
		if (weeklyVO.getWeeklyId() == null) {
			temp = weeklyBS.addWeekly(weeklyVO.cloneBO(), null);
		} else {
			WeeklyVO vo = weeklyBS.queryVOById(weeklyVO.getWeeklyId());
			temp = weeklyBS.modifyWeekly(weeklyVO.cloneBO(),
					vo.getWeeklyStaff());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	@RequestMapping("weeklyActionState")
	public void state(Long recordId) throws Exception {
		Weekly temp;
		WeeklyVO vo = weeklyBS.queryVOById(recordId);
		vo.setWeeklyState(Globals.L_WEEKLY_STATE_SUBMIT);
		temp = weeklyBS.modifyWeekly(vo.cloneBO(), vo.getWeeklyStaff());
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	@RequestMapping("weeklyActionUnState")
	public void unState(Long recordId) throws Exception {
		Weekly temp;
		WeeklyVO vo = weeklyBS.queryVOById(recordId);
		vo.setWeeklyState(Globals.L_WEEKLY_STATE_UNSUBMIT);
		temp = weeklyBS.modifyWeekly(vo.cloneBO(), vo.getWeeklyStaff());
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	public ModelAndView setGlobals(ModelAndView model, String url) {
		model.addObject("url", request.getContextPath() + "/weekly/" + url
				+ ".htm");
		model.addObject("staffMap", CmdUtils.getStaffMap());
		model.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		model.addObject("weeklyMap", Globals.M_WEEKLY_STATE);
		return model;
	}
}