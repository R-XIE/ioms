package com.iitdev.ioms.cost.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.cost.common.CmdUtils;
import com.iitdev.ioms.cost.common.Globals;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.cost.data.bo.CostType;
import com.iitdev.ioms.cost.data.vo.CostTypeVO;
import com.iitdev.ioms.cost.service.CostTypeBS;
/**
*
*费用类别 Action 
*
*/
@Controller
@RequestMapping("/cost")
public class CostTypeAction extends BaseAction<CostTypeVO>{
	public  CostTypeAction(){}
	@Autowired
	private CostTypeBS costTypeBS;
	/**************************************页面跳转******************************/
	 
	@RequestMapping("costTypeActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("cost/costTypeList");
		List<CostTypeVO> costTypeList= costTypeBS.queryVOListAll();
		view.addObject("list", costTypeList);
		view=setGlobals(view,"costTypeActionList");
		return view;
	}
	
	@RequestMapping("costTypeActionDel")
	public void del(Long recordId) throws Exception {
		CostTypeVO vo = costTypeBS.queryVOById(recordId);
		boolean res = costTypeBS.delCostType(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("costTypeActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("cost/costTypeInput");
		String type="";
		if(recordId!=null){
			CostTypeVO vo = costTypeBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view,"costTypeActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("costTypeActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("cost/costTypeInput");
		String type="";
		if(recordId!=null){
			CostTypeVO vo= costTypeBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"costTypeActionView");
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("costTypeActionSave")
	public void save(CostTypeVO costTypeVO) throws Exception {
		CostType temp;
		if (costTypeVO.getCostTypeId() == null) {
			temp = costTypeBS.addCostType(costTypeVO.cloneBO());
		}else{
			temp =costTypeBS.modifyCostType(costTypeVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public  ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("url", request.getContextPath()+"/cost/"+url+".htm");
		model.addObject("costTypeMap",Globals.M_COST_TYPE_TYPE);
		model.addObject("costTypeSuperMap",CmdUtils.getCostTypeMap());
		model.addObject("costTypeSuperRootMap",CmdUtils.getCostTypeSuperMap());
		return model;
	}
}