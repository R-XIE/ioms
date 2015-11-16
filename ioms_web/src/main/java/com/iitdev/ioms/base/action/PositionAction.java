package com.iitdev.ioms.base.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.base.data.bo.Position;
import com.iitdev.ioms.base.data.vo.PositionVO;
import com.iitdev.ioms.base.service.PositionBS;

/**
 * 
 * 岗位 Action
 * 
 */
@Controller
@RequestMapping("/base")
public class PositionAction extends BaseAction<Position> {
	@Autowired
	private PositionBS positionBS;
	
	public PositionAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("positionActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("base/positionList");
		List<PositionVO> postList= positionBS.queryVOAllList();
		Map<Long,String> branchMap=CmdUtils.getBranchMap();
		view.addObject("list", postList);
		view.addObject("branchMap", branchMap);
		return view;

	}
	@RequestMapping("positionActionDel")
	public void del(Long recordId) throws Exception {
		PositionVO vo = positionBS.queryVOById(recordId);
		boolean res = positionBS.delPosition(vo.cloneBO());
		super.printJson(res + "");
	}
	@RequestMapping("positionActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("base/positionInput");
		String type="";
		if(recordId!=null){
			PositionVO vo = positionBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		Map<Long,String> branchMap=CmdUtils.getBranchMap();
		view.addObject("branchMap", branchMap);
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("positionActionSave")
	public void save(Position position) throws Exception {
		Position temp;
		if (position.getPositionId() == null) {
			temp = positionBS.addPosition(position);
		}else{
			temp =positionBS.modifyPosition(position);
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

}