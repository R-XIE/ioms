package com.iitdev.ioms.operation.action;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.operation.common.Globals;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.operation.data.bo.Log;
import com.iitdev.ioms.operation.data.po.LogQueryForm;
import com.iitdev.ioms.operation.data.vo.LogVO;
import com.iitdev.ioms.operation.service.LogBS;
import com.iitdev.page.Result;
/**
*
*更改维护记录 Action 
*
*/
@Controller
@RequestMapping("/operation")
public class LogAction extends BaseAction<LogVO>{
	public  LogAction(){}
	@Autowired
	private LogBS logBS;
	/**************************************页面跳转******************************/
	//TO 查看界面
	
	@RequestMapping("logActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("operation/logList");
		List<LogVO> logList= logBS.queryVOListAll();
		view.addObject("list", logList);
		view=setGlobals(view,"logActionList");
		return view;
	}
	
	@RequestMapping("logActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("log") LogQueryForm log) {
		ModelAndView view = new ModelAndView("operation/logListAjax");
		Map<String,String> queryMap=log.buildQueryMap();
		Result<LogVO>  page=logBS.queryResultByPage(log.getP(), queryMap, log.getS());
		view.addObject("query", log);
		view.addObject("page", page);
		view=setGlobals(view,"logActionListAjax");
		return view;
	}
	
	@RequestMapping("logActionDel")
	public void del(Long recordId) throws Exception {
		LogVO vo = logBS.queryVOById(recordId);
		boolean res = logBS.delLog(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("logActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/logInput");
		String type="";
		if(recordId!=null){
			LogVO vo = logBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view,"logActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("logActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/logInput");
		String type="";
		if(recordId!=null){
			LogVO vo= logBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"logActionView");
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("logActionSave")
	public void save(LogVO logVO) throws Exception {
		Log temp;
		if (logVO.getLogId() == null) {
			temp = logBS.addLog(logVO.cloneBO());
		}else{
			//LogVO vo = logBS.queryVOById(logVO.getLogId());
			temp =logBS.modifyLog(logVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public  ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("url", request.getContextPath()+"/operation/"+url+".htm");
		model.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		model.addObject("logMap", Globals.M_LOG_STATE);
		return model;
	}
}