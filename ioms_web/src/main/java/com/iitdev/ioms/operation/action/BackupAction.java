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
import com.iitdev.ioms.operation.data.bo.Backup;
import com.iitdev.ioms.operation.data.po.BackupQueryForm;
import com.iitdev.ioms.operation.data.vo.BackupVO;
import com.iitdev.ioms.operation.service.BackupBS;
import com.iitdev.page.Result;
/**
*
*备份记录 Action 
*
*/
@Controller
@RequestMapping("/operation")
public class BackupAction extends BaseAction<BackupVO>{
	public  BackupAction(){}
	@Autowired
	private BackupBS backupBS;
	/**************************************页面跳转******************************/
	//TO 查看界面
	
	@RequestMapping("backupActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("operation/backupList");
		List<BackupVO> backupList= backupBS.queryVOListAll();
		view.addObject("list", backupList);
		view=setGlobals(view,"backupActionList");
		return view;
	}
	
	@RequestMapping("backupActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("backup") BackupQueryForm backup) {
		ModelAndView view = new ModelAndView("operation/backupListAjax");
		
		Map<String,String> queryMap=backup.buildQueryMap();
		Result<BackupVO>  page=backupBS.queryResultByPage(backup.getP(), queryMap, backup.getS());
		view.addObject("query", backup);
		view.addObject("page", page);
		view=setGlobals(view,"backupActionListAjax");
		return view;
	}
	
	@RequestMapping("backupActionDel")
	public void del(Long recordId) throws Exception {
		BackupVO vo = backupBS.queryVOById(recordId);
		boolean res = backupBS.delBackup(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("backupActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/backupInput");
		String type="";
		if(recordId!=null){
			BackupVO vo = backupBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view,"backupActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("backupActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("operation/backupInput");
		String type="";
		if(recordId!=null){
			BackupVO vo= backupBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"backupActionView");
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("backupActionSave")
	public void save(BackupVO backupVO) throws Exception {
		Backup temp;
		if (backupVO.getBackupId() == null) {
			temp = backupBS.addBackup(backupVO.cloneBO());
		}else{
			temp =backupBS.modifyBackup(backupVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public  ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("backupMap",Globals.M_BACKUP_STATE);
		model.addObject("pageMap",com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		model.addObject("url", request.getContextPath()+"/operation/"+url+".htm");
		return model;
	}
}