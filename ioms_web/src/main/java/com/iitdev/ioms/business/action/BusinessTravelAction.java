package com.iitdev.ioms.business.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.business.data.bo.BusinessTravel;
import com.iitdev.ioms.business.data.vo.BusinessTravelVO;
import com.iitdev.ioms.business.service.BusinessTravelBS;
import com.iitdev.ioms.member.common.CmdUtils;
/**
*
*出差 Action 
*
*/
@Controller
@RequestMapping("/business")
public class BusinessTravelAction extends BaseAction<BusinessTravelVO>{
	public  BusinessTravelAction(){}
	@Autowired
	private BusinessTravelBS businessTravelBS;
	/**************************************页面跳转******************************/
	//TO 查看界面
	
	@RequestMapping("businessTravelActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("business/businessTravelList");
		List<BusinessTravelVO> businessTravelList= businessTravelBS.queryVOListAll();
		view.addObject("list", businessTravelList);
		view=setGlobals(view,"businessTravelActionList");
		return view;
	}
	
	@RequestMapping("businessTravelActionDel")
	public void del(Long recordId) throws Exception {
		BusinessTravelVO vo = businessTravelBS.queryVOById(recordId);
		boolean res = businessTravelBS.delBusinessTravel(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("businessTravelActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("business/businessTravelInput");
		String type="";
		if(recordId!=null){
			BusinessTravelVO vo = businessTravelBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view,"businessTravelActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("businessTravelActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("business/businessTravelInput");
		String type="";
		if(recordId!=null){
			BusinessTravelVO vo= businessTravelBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"businessTravelActionView");
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("businessTravelActionSave")
	public void save(BusinessTravelVO businessTravelVO) throws Exception {
		BusinessTravel temp;
		if (businessTravelVO.getBusinessTravelId() == null) {
			temp = businessTravelBS.addBusinessTravel(businessTravelVO.cloneBO());
		}else{
			temp =businessTravelBS.modifyBusinessTravel(businessTravelVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("url", this.request.getContextPath()+"/business/"+url+".htm");
		model.addObject("staffMap", CmdUtils.getStaffMap());
		return model;
	}
}